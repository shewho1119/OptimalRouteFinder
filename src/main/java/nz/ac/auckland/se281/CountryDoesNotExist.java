package nz.ac.auckland.se281;

/**
 * Custom exception class to indicate that a country does not exist. This exception is thrown when
 * attempting to retrieve a country that does not exist.
 */
public class CountryDoesNotExist extends Exception {

  public CountryDoesNotExist(String countryName) {
    super("Country '" + countryName + "' does not exist.");
  }
}
