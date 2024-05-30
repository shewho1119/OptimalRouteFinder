package nz.ac.auckland.se281;

/** Represents a country class with its name, continent, and tax information. */
public class Country {
  private String name;
  private String continent;
  private int tax;

  /**
   * Constructs a new Country object with the argument name, continent, and tax rate.
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    result = prime * result + tax;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Country other = (Country) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (continent == null) {
      if (other.continent != null) return false;
    } else if (!continent.equals(other.continent)) return false;
    if (tax != other.tax) return false;
    return true;
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
