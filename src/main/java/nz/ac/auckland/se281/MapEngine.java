package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** This class is the main entry point. */
public class MapEngine {
  private Graph graph;

  /**
   * Constructs a new MapEngine object and make a graph. It also invokes the loadMap method to load
   * the map data into the graph.
   */
  public MapEngine() {
    graph = new Graph();
    loadMap();
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // Load countries
    for (String line : countries) {
      String[] parts = line.split(",");
      String name = parts[0];
      String continent = parts[1];
      int tax = Integer.parseInt(parts[2]);
      // creating new country object
      Country country = new Country(name, continent, tax);
      graph.addCountryNode(country);
    }

    // Load adjacencies
    for (String line : adjacencies) {
      String[] parts = line.split(",");
      String countryName = parts[0];
      // check if the country to load exists
      try {
        Country country = graph.getCountry(countryName);
        for (int i = 1; i < parts.length; i++) {
          // check if the neighbor country exists
          try {
            Country neighbor = graph.getCountry(parts[i]);
            graph.addEdge(country, neighbor);
          } catch (CountryDoesNotExist e) {
            MessageCli.INVALID_COUNTRY.printMessage(countryName);
          }
        }
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(countryName);
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();
    while (true) {
      String name = getCountryInput();
      // Check if the country exists
      try {
        Country country = graph.getCountry(name);
        // if exist, print the country info
        MessageCli.COUNTRY_INFO.printMessage(
            name, country.getContinent(), String.valueOf(country.getTax()));
        break;
      } catch (CountryDoesNotExist e) { // using exceptions to handle invalid country
        MessageCli.INVALID_COUNTRY.printMessage(name);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    String sourceName;
    String destinationName;
    Country source = null;
    Country destination = null;

    // Get the source country input
    MessageCli.INSERT_SOURCE.printMessage();
    // Check if the source country exists
    while (true) {
      sourceName = getCountryInput();
      try {
        source = graph.getCountry(sourceName);
        break;
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(sourceName);
      }
    }

    // Get the destination country input
    MessageCli.INSERT_DESTINATION.printMessage();
    // Check if the destination country exists
    while (true) {
      destinationName = getCountryInput();
      try {
        destination = graph.getCountry(destinationName);
        break;
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(destinationName);
      }
    }

    // Check if the same source and destination were chosen
    if (source.equals(destination)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    } else if (!(source.equals(destination))) {
      List<Country> path = graph.findShortestPath(source, destination);

      // Convert the countries in the path to type String
      List<String> pathString = new ArrayList<>();
      for (Country country : path) {
        pathString.add(country.getName());
      }
      String pathStringJoined = String.join(", ", pathString);
      MessageCli.ROUTE_INFO.printMessage("[" + pathStringJoined + "]");

      // get the continents in type String from the countries
      Set<String> continentString = new LinkedHashSet<>();
      for (Country country : path) {
        continentString.add(country.getContinent());
      }
      String continentStringJoined = String.join(", ", continentString);
      MessageCli.CONTINENT_INFO.printMessage("[" + continentStringJoined + "]");

      // Calculate and print the total tax
      int totalTax = 0;
      for (int i = 1; i < path.size(); i++) {
        Country country = path.get(i);
        totalTax += country.getTax();
      }
      MessageCli.TAX_INFO.printMessage(Integer.toString(totalTax));
    }
  }

  /**
   * Helper method to get the country input, and capitalize first letter
   *
   * @return The corrected country name inputted by the user
   */
  public String getCountryInput() {
    while (true) {
      String countryName = Utils.scanner.nextLine();
      String correctedCountryName = Utils.capitalizeFirstLetterOfEachWord(countryName);
      return correctedCountryName;
    }
  }
}
