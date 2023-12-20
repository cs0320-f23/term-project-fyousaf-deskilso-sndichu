package edu.brown.cs.student.databaserandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.beust.ah.A;
import com.google.gson.JsonObject;
import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasedata.OutfitLog;
import edu.brown.cs.student.main.databasedata.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

/**
 * Random testing for the database.
 */
public class DatabaseRandomTest {

  /**
   * Gets a random string.
   *
   * @return a random string
   */
  public static String getRandomStringBounded(int length) {
    final ThreadLocalRandom r = ThreadLocalRandom.current();
    StringBuilder sb = new StringBuilder();
    for (int iCount = 0; iCount < length; iCount++) {
      // upper-bound is exclusive
      int code = r.nextInt(45, 126 + 1);
      sb.append((char) code);
    }
    return sb.toString();
  }

  /**
   * Gets a random outfit log.
   *
   * @return an ourfit log with random values.
   */
  public static OutfitLog getRandomOutfitLog() {
    final ThreadLocalRandom r = ThreadLocalRandom.current();
    long id = r.nextLong();
    int itemLength = r.nextInt(10);
    int numberLength = r.nextInt(10);
    List<String> outfits = new ArrayList<>();
    // Beware: This excludes a lot of punctuation in the interest of not causing problems for CSV...
    for (int i = 0; i < numberLength; i++) {
      outfits.add(getRandomStringBounded(itemLength));
    }
    double determiner = r.nextDouble(1);
    if (determiner < 0.5) {
      return new OutfitLog(outfits, Status.OUTSIDE);
    } else {
      return new OutfitLog(outfits, Status.INSIDE);
    }
  }


  /**
   * Fuzz testing for the database functionality.
   */
  @Test
  public void fuzzTestFilter() {
    DatabaseDataSource database = new DatabaseDataSource();
    List<OutfitLog> outfits = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      OutfitLog outfitLog = getRandomOutfitLog();
      outfits.add(outfitLog);
      String status;
      if (outfitLog.getStatus() == Status.INSIDE) {
        status = "inside";
      } else {
        status = "outside";
      }
      database.write(null, outfitLog.getOutfit(), null, status);
    }
    assertEquals(outfits, database.readAllOutfits());
    database.deleteAll();
  }
}

