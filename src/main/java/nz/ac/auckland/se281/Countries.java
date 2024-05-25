package nz.ac.auckland.se281;

public class Countries {
  private String name;
  private String continent;
  private int tax;

  public Countries(String name, String continent, int tax) {
    this.name = name;
    this.continent = continent;
    this.tax = tax;
  }

  public String getName() {
    return name;
  }

  public String getContinent() {
    return continent;
  }

  public int getTax() {
    return tax;
  }
}
