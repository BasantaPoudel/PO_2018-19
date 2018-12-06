/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchDisciplineNewException extends Exception{


  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public NoSuchDisciplineNewException(String name) {
    _name = name;
  }



}
