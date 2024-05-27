package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * A class to keep track of countries(nodes) and their relationships(edges). This class enables us
 * to add new Nodes and new Edges and create algorithms to find the shortest path between two nodes.
 */
public class Graph {
  private Map<String, Country> countries;
  private Map<Country, Set<Country>> adjNodes;

  public Graph() {
    countries = new HashMap<>();
    adjNodes = new HashMap<>();
  }

  public void addCountryNode(Country country) {
    countries.putIfAbsent(country.getName(), country);
    adjNodes.putIfAbsent(country, new LinkedHashSet<>());
  }

  public void addEdge(Country country1, Country country2) {
    adjNodes.get(country1).add(country2);
  }

  /**
   * Getter method for country with the specified name from the map of countries. If the country
   * does not exist, throws a CountryDoesNotExist exception with a custom error message indicating
   * the name of the country that was not found.
   *
   * @param name The name of the country to return
   * @return The country with the specified name
   * @throws CountryDoesNotExist If the country with the specified name does not exist
   */
  public Country getCountry(String name) throws CountryDoesNotExist {
    Country country = countries.get(name);
    // If the country is not found, throw an exception
    if (countries.get(name) == null) {
      throw new CountryDoesNotExist(name);
    }
    return country;
  }

  /**
   * Finds the shortest path from the source country to the destination country using breadth-first
   * search (BFS) algorithm.
   *
   * @param source The starting country of the path
   * @param destination The destination country of the path
   * @return A list of countries representing the shortest path from the source country to the
   *     destination country, or null if no path is found
   */
  public List<Country> findShortestPath(Country source, Country destination) {

    Map<Country, Country> parentMap = new HashMap<>(); // hashmap to record the linked countries
    Set<Country> visited = new HashSet<>(); // hashset to record the visited countries
    Queue<Country> queue = new LinkedList<>(); // queue to store the countries to be visited

    // initialize the source country
    queue.add(source);
    visited.add(source);
    parentMap.put(source, null);

    // Perform BFS until the queue is empty
    while (!queue.isEmpty()) {
      Country current = queue.poll();

      // check all the neighbors of the current country
      for (Country neighbor : adjNodes.get(current)) {
        // country can only be visited once
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
          parentMap.put(neighbor, current);

          // if the destination is found, return the path
          if (neighbor.equals(destination)) {
            return createPath(parentMap, source, destination);
          }
        }
      }
    }
    return null;
  }

  /**
   * Creates a path from the destination country to the source country based on the parentMap which
   * contains mappings from each country to its parent country. The path is constructed by
   * traversing the parentMap from the destination country back to the source country.
   *
   * @param parentMap A map containing mappings from each country to its parent country
   * @param source The origin country
   * @param destination The destination country
   * @return path A linked list representing the fastest route from the source country to the
   *     destination country
   */
  private List<Country> createPath(
      Map<Country, Country> parentMap, Country source, Country destination) {

    // Initialize a linked list to store the path
    LinkedList<Country> path = new LinkedList<>();

    // Traverse the parentMap from the destination country back to the source country
    for (Country i = destination; i != null; i = parentMap.get(i)) {
      // Add each country to the beginning of the linked list
      path.addFirst(i);
    }
    return path;
  }
}
