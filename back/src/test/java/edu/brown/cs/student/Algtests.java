package edu.brown.cs.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasedata.OutfitLog;
import edu.brown.cs.student.main.databasedata.Status;
import edu.brown.cs.student.main.recommendationalg.Constants;
import edu.brown.cs.student.main.recommendationalg.CustomException;
import edu.brown.cs.student.main.recommendationalg.RecommendationSource;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/** JUnit test class for the RecommendationSource functionality. */
public class Algtests {

  private RecommendationSource recommendationSource;

  /** Set up the RecommendationSource with mock data for testing. */
  @Before
  public void setUp() {
    // Set up the RecommendationSource with mock data
    // Mock outfit logs with two different outfits
    OutfitLog log1 =
        new OutfitLog(
            Arrays.asList("hat", "sunglasses", "t-shirt", "jeans", "belt"), Status.OUTSIDE);
    OutfitLog log2 =
        new OutfitLog(
            Arrays.asList("dress shirt", "tie", "coat", "umbrella", "belt"), Status.OUTSIDE);
    OutfitLog indoorOutfit1 =
        new OutfitLog(Arrays.asList("sweater", "jeans", "sneakers", "watch"), Status.INSIDE);
    OutfitLog indoorOutfit2 =
        new OutfitLog(Arrays.asList("blouse", "skirt", "flats", "necklace"), Status.INSIDE);
    OutfitLog indoorOutfit3 =
        new OutfitLog(Arrays.asList("cardigan", "leggings", "boots", "bracelet"), Status.INSIDE);
    OutfitLog indoorOutfit4 =
        new OutfitLog(Arrays.asList("turtleneck", "slacks", "loafers", "watch"), Status.INSIDE);
    OutfitLog indoorOutfit5 =
        new OutfitLog(Arrays.asList("blazer", "dress pants", "heels", "earrings"), Status.INSIDE);

    OutfitLog outdoorOutfit1 =
        new OutfitLog(Arrays.asList("jacket", "jeans", "sneakers", "hat", "belt"), Status.OUTSIDE);
    OutfitLog outdoorOutfit2 =
        new OutfitLog(Arrays.asList("raincoat", "pants", "boots", "umbrella"), Status.OUTSIDE);
    OutfitLog outdoorOutfit3 =
        new OutfitLog(Arrays.asList("hoodie", "shorts", "sandals", "sunglasses"), Status.OUTSIDE);

    List<OutfitLog> outfitLogs = new ArrayList<>();
    outfitLogs.add(log1);
    outfitLogs.add(log2);
    outfitLogs.add(indoorOutfit1);
    outfitLogs.add(indoorOutfit2);
    outfitLogs.add(indoorOutfit3);
    outfitLogs.add(indoorOutfit4);
    outfitLogs.add(indoorOutfit5);
    outfitLogs.add(outdoorOutfit1);
    outfitLogs.add(outdoorOutfit2);
    outfitLogs.add(outdoorOutfit3);

    // Mock DatabaseDataSource
    DatabaseDataSource databaseDataSource = new DatabaseDataSource();
    Constants constants = new Constants();

    recommendationSource =
        new RecommendationSource(constants.compatibilityRules, databaseDataSource, outfitLogs);
  }

  /** Test the filtering of outfit logs based on the status (INSIDE/OUTSIDE). */
  @Test
  public void testFilterOutfitLogs() {
    List<OutfitLog> sunnyLogs = recommendationSource.filterOutfitLogs(Status.INSIDE);
    List<OutfitLog> rainyLogs = recommendationSource.filterOutfitLogs(Status.OUTSIDE);

    assertEquals(5, sunnyLogs.size());
    assertEquals(5, rainyLogs.size());
  }

  /** Test the compatibility check for an outfit log. */
  @Test
  public void testIsCompatible() {
    OutfitLog compatibleLog =
        new OutfitLog(Arrays.asList("hat", "t-shirt", "jeans"), Status.OUTSIDE);
    OutfitLog incompatibleLog =
        new OutfitLog(
            Arrays.asList("gloves", "tie", "coat", "umbrella", "sunglasses"), Status.OUTSIDE);
    assertTrue(recommendationSource.isCompatible(compatibleLog));
    assertFalse(recommendationSource.isCompatible(incompatibleLog));
  }

  /** Test the retrieval of popular accessories from the outfit logs. */
  @Test
  public void testGetPopularAccessories() {
    String popularAccessories = recommendationSource.getPopularAccessories();
    assertTrue(popularAccessories.contains("belt"));
  }

  /**
   * Test the generation of outfit recommendations.
   *
   * @throws CustomException if there is an issue generating recommendations.
   */
  @Test
  public void testGenerateRecommendations() throws CustomException {
    List<List<String>> recommendationsIn = recommendationSource.generateRecommendations();
    List<List<String>> recommendationsOut = recommendationSource.generateRecommendations();

    // Ensure that recommendations include compatible outfits
    assertTrue(
        recommendationsIn.contains(Arrays.asList("hat", "sunglasses", "t-shirt", "jeans", "belt")));
    assertTrue(
        recommendationsOut.contains(Arrays.asList("raincoat", "pants", "boots", "umbrella")));
  }

  /**
   * Test handling of empty outfit logs during recommendation generation.
   *
   * @throws CustomException if there is an issue generating recommendations.
   */
  @Test
  public void testEmptyLogs() throws CustomException {
    List<OutfitLog> emptyOutfitLogs = new ArrayList<>();
    RecommendationSource emptyLogsSource =
        new RecommendationSource(new HashMap<>(), new DatabaseDataSource(), emptyOutfitLogs);
    try {
      emptyLogsSource.generateRecommendations();
    } catch (CustomException e) {
      assertEquals("Logs is Empty", e.getMessage());
    }
  }

  /** Test compatibility check when there are no compatibility rules defined. */
  @Test
  public void testNoCompatibilityRules() {
    List<OutfitLog> myLogs = new ArrayList<>();
    myLogs.add(
        new OutfitLog(
            Arrays.asList("gloves", "tie", "coat", "umbrella", "sunglasses"), Status.OUTSIDE));
    RecommendationSource emptyCompatibilitySource =
        new RecommendationSource(new HashMap<>(), new DatabaseDataSource(), myLogs);
    assertTrue(
        emptyCompatibilitySource.isCompatible(
            new OutfitLog(
                Arrays.asList("gloves", "tie", "coat", "umbrella", "sunglasses"), Status.OUTSIDE)));
  }
}
