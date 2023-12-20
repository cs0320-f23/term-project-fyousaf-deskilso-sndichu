package edu.brown.cs.student.main.recommendationalg;

import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasedata.OutfitLog;
import edu.brown.cs.student.main.databasedata.Status;
import java.util.*;

/**
 * RecommendationSource class provides outfit recommendations based on compatibility rules,
 * outfit logs, and current weather conditions.
 */
public class RecommendationSource {
  private List<OutfitLog> outfitLogs;
  private Map<String, List<String>> compatibilityRules;
  private DatabaseDataSource databaseDataSource;

  /**
   * Constructor for RecommendationSource.
   *
   * @param compatibilityRules   Map of compatibility rules between clothing items.
   * @param databaseDataSource   Database data source for additional information.
   * @param outfitLogs           List of outfit logs containing historical data.
   */
  public RecommendationSource(
      Map<String, List<String>> compatibilityRules,
      DatabaseDataSource databaseDataSource,
      List<OutfitLog> outfitLogs) {
    this.compatibilityRules = compatibilityRules;
    this.databaseDataSource = databaseDataSource;
    this.outfitLogs = outfitLogs;
  }

  /**
   * Getter for outfitLogs.
   *
   * @return List of outfit logs.
   */
  public List<OutfitLog> getOutfitLogs() {
    return outfitLogs;
  }

  /**
   * Generates outfit recommendations based on compatibility rules, outfit logs, and current weather.
   *
   * @return List of recommended outfits.
   * @throws CustomException if there is an issue generating recommendations.
   */
  public List<List<String>> generateRecommendations() throws CustomException{
    List<List<String>> recommendations = new ArrayList<>();
    if(this.outfitLogs.isEmpty()){
      throw new CustomException("Logs is Empty");
    }else{
    Status currentWeather = this.outfitLogs.get(this.outfitLogs.size()-1).status();
    List<OutfitLog>  filteredLogs = filterOutfitLogs(currentWeather);
    if(filteredLogs.isEmpty()){
      throw new CustomException("FilteredLogs is Empty");
    }
    for (OutfitLog log : filteredLogs) {
      if (isCompatible(log)) {
        if(recommendations.size()<=3){
        recommendations.add(log.outfit());}
      }
    }
    if(recommendations.isEmpty()){
      throw new CustomException("No recommendation matches your preference");
    }
    return recommendations;
  }}

  /**
   * Filters outfit logs based on the current weather status.
   *
   * @param currentWeather Current weather status (INSIDE or OUTSIDE).
   * @return List of outfit logs matching the current weather.
   */
  public List<OutfitLog> filterOutfitLogs(Status currentWeather) {
    List<OutfitLog> filteredLogs = new ArrayList<>();
    for (OutfitLog log : this.outfitLogs) {
      if (log.status().equals(currentWeather)) {
        filteredLogs.add(log);
      }
    }
    return filteredLogs;
  }

  /**
   * Checks if an outfit is compatible based on the defined compatibility rules.
   *
   * @param log OutfitLog to check for compatibility.
   * @return True if the outfit is compatible, false otherwise.
   */
  public boolean isCompatible(OutfitLog log) {

    List<String> clothingPieces = log.outfit();

    for (String piece : clothingPieces) {
      if (compatibilityRules.containsKey(piece)) {
        List<String> incompatiblePieces = compatibilityRules.get(piece);
        if (containsAny(clothingPieces, incompatiblePieces)) {
          return false; // Incompatible combination found
        }
      }
    }
    return true;
  }
  /**
   * Helper method to check if any element in list1 is present in list2.
   *
   * @param list1 List to check for elements.
   * @param list2 List containing elements to check against.
   * @return True if any element in list1 is present in list2, false otherwise.
   */
  private boolean containsAny(List<String> list1, List<String> list2) {
    // Helper method to check if any element in list1 is present in list2
    for (String element : list1) {
      if (list2.contains(element.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves popular accessories from the outfit logs.
   *
   * @return String representing the popular accessory.
   */
  public String getPopularAccessories() {
    Map<String, Integer> accessoryCounts = new HashMap<>();

    // Set of accessory names in a case-insensitive manner
    Set<String> accessories =
        Set.of("hat", "sunglasses", "necklace", "gloves", "belt", "tote bag", "tie", "watch");

    for (OutfitLog log : this.outfitLogs) {
      List<String> outfitAccessories = log.outfit();

      for (String accessory : outfitAccessories) {
        if (accessories.contains(accessory)) {
          // Convert accessory to lowercase to ensure case-insensitive comparison
          accessory = accessory.toLowerCase();

          accessoryCounts.put(accessory, accessoryCounts.getOrDefault(accessory, 0) + 1);
        }
      }
    }

    String popularAccessories = "";

    int maxCount = 0;

    // Find the maximum count
    for (int count : accessoryCounts.values()) {
      maxCount = Math.max(maxCount, count);
    }

    // Collect all accessories with the maximum count
    for (Map.Entry<String, Integer> entry : accessoryCounts.entrySet()) {
      if (entry.getValue() == maxCount) {
        popularAccessories = entry.getKey();
      }
    }

    return popularAccessories;
  }
}
