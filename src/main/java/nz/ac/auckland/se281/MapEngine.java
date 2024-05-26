package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {
  private Graph graph;

  public MapEngine() {
    // add other code here if you want
    graph = new Graph();
    loadMap(); // keep this mehtod invocation
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
      Country country = new Country(name, continent, tax);
      graph.addCountryNode(country);
    }

    // Load adjacencies
    for (String line : adjacencies) {
      String[] parts = line.split(",");
      String countryName = parts[0];
      try {
        Country country = graph.getCountry(countryName);
        for (int i = 1; i < parts.length; i++) {
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
      try {
        Country country = graph.getCountry(name);
        MessageCli.COUNTRY_INFO.printMessage(
            name, country.getContinent(), String.valueOf(country.getTax()));
        break;
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(name);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    String sourceName;
    String destinationName;
    MessageCli.INSERT_SOURCE.printMessage();
    while (true) {
      sourceName = getCountryInput();
      try {
        Country country = graph.getCountry(sourceName);
        MessageCli.COUNTRY_INFO.printMessage(
            sourceName, country.getContinent(), String.valueOf(country.getTax()));
        break;
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(sourceName);
      }
    }

    MessageCli.INSERT_DESTINATION.printMessage();
    while (true) {
      destinationName = getCountryInput();
      try {
        Country country = graph.getCountry(destinationName);
        MessageCli.COUNTRY_INFO.printMessage(
            destinationName, country.getContinent(), String.valueOf(country.getTax()));
        break;
      } catch (CountryDoesNotExist e) {
        MessageCli.INVALID_COUNTRY.printMessage(destinationName);
      }
    }

    MessageCli.ROUTE_INFO.printMessage("[" + sourceName + ", " + destinationName + "]");
  }

  public String getCountryInput() {
    while (true) {
      String countryName = Utils.scanner.nextLine();
      String correctedCountryName = Utils.capitalizeFirstLetterOfEachWord(countryName);
      return correctedCountryName;
    }
  }
}
