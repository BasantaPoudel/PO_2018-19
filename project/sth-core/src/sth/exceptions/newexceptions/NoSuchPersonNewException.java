/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchPersonNewException extends Exception{


  /** Person id. */
  private int _id;

  /**
   * @param id
   */
  public NoSuchPersonNewException(int id) {
    _id = id;
  }


}
