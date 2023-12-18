package edu.brown.cs.student.main.databasedata;

import java.util.List;

/**
 * Interface representing a database datasource.
 */
public interface Database {

  /**
   * Method to write to a database.
   */
  void write(Integer rating, List<String> clothing, String timestamp, String status);

  /**
   * Method to read from the database.
   */
  String read(int index);

  /**
   * Method to go over all outfit logs in the database and then return outfit objects.
   *
   * @return a list of outfit logs
   */
  List<OutfitLog> readAllOutfits();
}
