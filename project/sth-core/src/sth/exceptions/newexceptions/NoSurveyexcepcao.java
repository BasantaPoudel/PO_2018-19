
package sth.exceptions.newexceptions;
import java.lang.Exception;


public class NoSurveyexcepcao extends Exception{
  /** Discipline name. */
  private String _discipline;

  /** Project name. */
  private String _project;

  /**
   * @param discipline
   * @param project
   */
  public NoSurveyexcepcao(String discipline, String project) {
    _discipline = discipline;
    _project = project;
  }

}
