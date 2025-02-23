/**
 *
 */
package sth.exceptions.newexceptions;
import java.lang.Exception;



/**
 *
 */
public class SurveyFinishedNewException extends Exception{


  /** Discipline name. */
  private String _discipline;

  /** Project name. */
  private String _project;

  /**
   * @param discipline
   * @param project
   */
  public SurveyFinishedNewException(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

}
