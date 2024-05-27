package nz.ac.auckland.se281;

public class CountryDoesNotExist extends Exception {

  public CountryDoesNotExist(String countryName) {
    super("Country '" + countryName + "' does not exist.");
  }
}
