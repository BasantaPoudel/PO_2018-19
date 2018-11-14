package sth.exceptions;

/**
 * Class for representing a read error.
 */
public class UnknownDataException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public UnknownDataException() {
    // do nothing
  }

  /**
   * @param description
   */
  public UnknownDataException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public UnknownDataException(Exception cause) {
    super(cause);
  }

}
