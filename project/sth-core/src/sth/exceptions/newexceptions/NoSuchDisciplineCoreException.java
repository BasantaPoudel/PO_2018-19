/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchDisciplineCoreException extends Exception{


  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public NoSuchDisciplineCoreException(String name) {
    _name = name;
  }



}
