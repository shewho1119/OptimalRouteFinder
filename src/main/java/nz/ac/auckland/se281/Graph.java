package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<String, Country> countries;
  private Map<Country, List<Country>> adjNodes;

  public Graph() {
    this.countries = new HashMap<>();
    this.adjNodes = new HashMap<>();
  }
}
