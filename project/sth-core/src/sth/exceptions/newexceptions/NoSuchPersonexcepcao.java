/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchPersonexcepcao extends Exception{


  /** Person id. */
  private int _id;

  /**
   * @param id
   */
  public NoSuchPersonexcepcao(int id) {
    _id = id;
  }


}
