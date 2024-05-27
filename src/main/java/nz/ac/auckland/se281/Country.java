package nz.ac.auckland.se281;

/** Represents a country class with its name, continent, and tax information. */
public class Country {
  private String name;
  private String continent;
  private int tax;

  /**
   * Constructs a new Country object with the specified name, continent, and tax rate.
   *
   * @param name The name of the country
   * @param continent The continent to which the country belongs
   * @param tax Taxes that need to be paid to cross the border
   */
  public Country(String name, String continent, int tax) {
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
