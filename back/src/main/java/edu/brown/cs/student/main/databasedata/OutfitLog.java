package edu.brown.cs.student.main.databasedata;

import java.util.List;

/**
 * Record representing an outfit log for use by the algorithm.
 *
 * @param outfit pieces of clothing
 * @param status indoor or outdoor
 */
public record OutfitLog(List<String> outfit, Status status) {

}
