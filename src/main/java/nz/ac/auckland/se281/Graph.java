package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

  public Country getCountry(String name) throws CountryDoesNotExist {
    Country country = countries.get(name);
    if (countries.get(name) == null) {
      throw new CountryDoesNotExist();
    }
    return country;
  }

  public List<Country> shortestPathBFS(Country source, Country destination) {
    Map<Country, Country> parentMap = new HashMap<>();
    Set<Country> visited = new HashSet<>();
    Queue<Country> queue = new LinkedList<>();

    queue.add(source);
    visited.add(source);
    parentMap.put(source, null);

    for (int i = 0; i < 3; i++) {
      Country current = queue.poll();

      for (Country neighbor : adjNodes.get(current)) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
          parentMap.put(neighbor, current);

          if (neighbor.equals(destination)) {
            return createPath(parentMap, source, destination);
          }
        }
      }
    }
    return null;
  }

  private List<Country> createPath(
      Map<Country, Country> parentMap, Country source, Country destination) {

    LinkedList<Country> path = new LinkedList<>();

    for (Country i = destination; i != null; i = parentMap.get(i)) {
      path.addFirst(i);
    }
    return path;
  }
}
