/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchPersonCoreException extends Exception{


  /** Person id. */
  private int _id;

  /**
   * @param id
   */
  public NoSuchPersonCoreException(int id) {
    _id = id;
  }


}
