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
      Country country = graph.getCountry(countryName);
      for (int i = 1; i < parts.length; i++) {
        Country neighbor = graph.getCountry(parts[i]);
        graph.addEdge(country, neighbor);
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
