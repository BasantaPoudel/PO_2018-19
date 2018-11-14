package sth.exceptions;

/**
 * Class for representing a read error.
 */
public class UnknownAgentException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public UnknownAgentException() {
    // do nothing
  }

  /**
   * @param description
   */
  public UnknownAgentException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public UnknownAgentException(Exception cause) {
    super(cause);
  }

}
