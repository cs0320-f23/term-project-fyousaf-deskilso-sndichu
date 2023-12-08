package edu.brown.cs.student.main.databasedata;

import java.util.List;

public class DatabaseDataSource implements Database {
  // TODO: Choose database library and then add reading/writing functionality here

  private int entryNumbers = 0;
  /**
   * Method to write to a database.
   *
   * @param id
   * @param rating
   * @param clothing
   * @param timestamp
   */
  @Override
  public void write(String id, Integer rating, List<String> clothing, String timestamp) {

  }

  /** */
  @Override
  public void read() {}

  public int getEntryNumbers() {
    return entryNumbers;
  }

  public void setEntryNumbers(int entryNumbers) {
    this.entryNumbers = entryNumbers;
  }
}
