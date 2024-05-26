package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
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
}
