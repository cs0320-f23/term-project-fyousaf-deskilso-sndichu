package edu.brown.cs.student.main.databasedata;

import java.util.List;

/**
 * Interface representing a database datasource.
 */
public interface Database {

  /**
   * Method to write to a database.
   */
  public void write(Integer rating, List<String> clothing, String timestamp, String status);

  /**
   * Method to read to a database
   */
  public String read(int index);

  public List<OutfitLog> readAllOutfits();

  // TODO (eventually): added mocked database class and cached database class implementing this
  // interface potentially
}
