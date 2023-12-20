package edu.brown.cs.student.main.recommendationalg;

/**
 * A custom class to ensure any errors incurred do not crash the program but instead return
 * informative messages
 */
public class CustomException extends Exception {
  private final Throwable cause;

  /**
   * Constructor for our custom exception
   *
   * @param message
   */
  public CustomException(String message) {
    super(message);
    this.cause = null;
  }

  public CustomException(String message, Throwable cause) {
    super(message);
    this.cause = cause;
  }
}
