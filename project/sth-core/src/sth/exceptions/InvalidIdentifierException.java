package sth.exceptions;

 /**
 * Class for representing a read error.
 */
public class InvalidIdentifierException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public InvalidIdentifierException() {
    // do nothing
  }

  /**
   * @param description
   */
  public InvalidIdentifierException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public InvalidIdentifierException(Exception cause) {
    super(cause);
  }

}
