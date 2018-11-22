package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.4.3. Show survey results.
 */
public class DoShowSurveyResults extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectname;
  Input<String> _disciplinename;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    // try{
    // // _display.add(_receiver.showSurveyResults()));
    // // _display.display();
    // }
    // catch ( NoSurveyException e) {
   //   throw new NoSurveyException(_disciplinename,_projectname);
   // }
   // catch(NoSuchProjectException e){
   //   throw new NoSuchProjectException(_disciplinename,_projectname);
   // }
   // catch(NoSuchDisciplineException e){
   //   throw new NoSuchDisciplineException(_disciplinename);
   //}
  }

}
