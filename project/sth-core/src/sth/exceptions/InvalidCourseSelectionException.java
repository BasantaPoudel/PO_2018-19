package sth.exceptions;

/**
 * Class for representing a read error.
 */
public class InvalidCourseSelectionException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public InvalidCourseSelectionException() {
    // do nothing
  }

  /**
   * @param description
   */
  public InvalidCourseSelectionException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public InvalidCourseSelectionException(Exception cause) {
    super(cause);
  }

}
