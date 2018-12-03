package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.exceptions.newexceptions.NoSurveyCoreException;
import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;

import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;
/**
 * 4.4.3. Show survey results.
 */
public class DoShowSurveyResults extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    //FIXME initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
    // _display.add(_receiver.showSurveyResult(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.showSurveyResult(_disciplineName.value(),_projectName.value());
    }
    catch ( NoSurveyCoreException e) {
     throw new NoSurveyException(_disciplineName.value(),_projectName.value());
   }
   catch(NoSuchProjectCoreException e){
     throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
   }
   catch(NoSuchDisciplineCoreException e){
     throw new NoSuchDisciplineException(_disciplineName.value());
   }
  }

}
