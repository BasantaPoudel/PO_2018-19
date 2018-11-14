package sth.exceptions;
/**
 * Class for representing a read error.
 */
public class PublicationExistsException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /**
   * Default constructor
   */
  public PublicationExistsException() {
    // do nothing
  }

  /**
   * @param description
   */
  public PublicationExistsException(String description) {
    super(description);
  }

  /**
   * @param cause
   */
  public PublicationExistsException(Exception cause) {
    super(cause);
  }

}
