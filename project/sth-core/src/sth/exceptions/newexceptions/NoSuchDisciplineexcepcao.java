/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class NoSuchDisciplineexcepcao extends Exception{


  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public NoSuchDisciplineexcepcao(String name) {
    _name = name;
  }



}
