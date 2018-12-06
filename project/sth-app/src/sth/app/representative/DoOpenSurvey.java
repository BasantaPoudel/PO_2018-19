package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.OpeningSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;

import sth.exceptions.newexceptions.NoSurveyNewException;
import sth.exceptions.newexceptions.OpeningSurveyNewException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;

//FIXME import other classes if needed

/**
 * 4.5.3. Open survey.
 */
public class DoOpenSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
    //FIXME initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    // _display.add(_receiver.doOpennnn(_projectName.value())));
    // _display.display();
    try{
    // _display.add(_receiver.openSurvey(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.openSurvey(_disciplineName.value(),_projectName.value());
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyException(_disciplineName.value(),_projectName.value());
    }
     catch(OpeningSurveyNewException e){
       throw new OpeningSurveyException(_disciplineName.value(),_projectName.value());
     }
     catch(NoSuchProjectNewException e){
       throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
     }
     catch(NoSuchDisciplineNewException e){
       throw new NoSuchDisciplineException(_disciplineName.value());
     }
  }

}
