/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class DuplicateProjectNewException extends Exception{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  /** Discipline name. */
  private String _discipline;

  /** Project name. */
  private String _project;

  /**
   * @param discipline
   * @param project
   */
  public DuplicateProjectNewException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }



}
