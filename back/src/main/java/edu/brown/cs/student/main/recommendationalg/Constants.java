package edu.brown.cs.student.main.recommendationalg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class houses our compatibility rules as a hashmap to ensure the code is more extensible to suit changing preferences in fashion.
 */
public class Constants {

  public final Map<String, List<String>> compatibilityRules;

  public Constants() {
    compatibilityRules = new HashMap<>();
    initializeCompatibilityRules();
  }

  /**
   * Method to initialize the hashmap
   */
  private void initializeCompatibilityRules() {
    // Rule 1
    compatibilityRules.put("hoodie", List.of("sweater vest"));

    // Rule 2
    compatibilityRules.put("sweat shirt", List.of("sweater vest"));

    // Rule 3
    compatibilityRules.put("t-Shirt", List.of("dress Shirt", "blouse"));

    // Rule 4
    compatibilityRules.put("dress Shirt", List.of("t-Shirt", "blouse"));

    // Rule 5
    compatibilityRules.put("blouse", List.of("t-Shirt", "dress shirt"));

    // Rule 6
    compatibilityRules.put("jeans", List.of("dress pants", "skirt"));

    // Rule 7
    compatibilityRules.put("dress Pants", List.of("jeans", "skirt"));

    // Rule 8
    compatibilityRules.put("skirt", List.of("jeans", "dress pants"));

    // Rule 9
    compatibilityRules.put("sneakers", List.of("loafers", "heels", "flats", "sandals"));

    // Rule 10
    compatibilityRules.put("loafers", List.of("sneakers", "heels", "flats", "sandals"));

    // Rule 11
    compatibilityRules.put("heels", List.of("sneakers", "loafers", "flats", "sandals"));

    // Rule 12
    compatibilityRules.put("flats", List.of("sneakers", "loafers", "heels", "sandals"));

    // Rule 13
    compatibilityRules.put("foots", List.of("sandals"));

    // Rule 14
    compatibilityRules.put("umbrella", List.of("sunglasses"));

    // Rule 15
    compatibilityRules.put("sunglasses", List.of("umbrella", "raincoat"));
  }
}
