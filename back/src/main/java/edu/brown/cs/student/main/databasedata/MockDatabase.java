package edu.brown.cs.student.main.databasedata;

import java.util.List;

/**
 * Mocked database class.
 */
public class MockDatabase implements Database {

  /**
   * Method to write to a database.
   *
   * @param rating    a rating.
   * @param clothing  the clothing.
   * @param timestamp the timestamp.
   * @param status    the status.
   */
  @Override
  public void write(Integer rating, List<String> clothing, String timestamp, String status) {
  }

  /**
   * Method to read from the database.
   *
   * @param index an index.
   */
  @Override
  public String read(int index) {
    return null;
  }

  /**
   * Method to go over all outfit logs in the database and then return outfit objects.
   *
   * @return a list of outfit logs
   */
  @Override
  public List<OutfitLog> readAllOutfits() {
    return List.of(new OutfitLog(List.of("hoodie, shirt"), Status.OUTSIDE),
        new OutfitLog(List.of("pants, shorts"), Status.INSIDE));
  }
}
