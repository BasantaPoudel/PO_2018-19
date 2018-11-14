package sth.exceptions;

/**
 * Class for representing a read error.
 */
public class ClientExistsException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public ClientExistsException() {
    // do nothing
  }

  /**
   * @param description
   */
  public ClientExistsException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public ClientExistsException(Exception cause) {
    super(cause);
  }

}
