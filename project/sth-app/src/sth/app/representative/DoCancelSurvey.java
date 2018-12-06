package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;


import sth.exceptions.newexceptions.NoSurveyNewException;
import sth.exceptions.newexceptions.NonEmptySurveyNewException;
import sth.exceptions.newexceptions.SurveyFinishedNewException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;


import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NonEmptySurveyException;
import sth.app.exceptions.SurveyFinishedException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;
//FIXME import other classes if needed

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
    //FIXME initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName());

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();
    try{
    // _display.add(_receiver.cancelSurvey(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.cancelSurvey(_disciplineName.value(),_projectName.value());
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyException(_disciplineName.value(),_projectName.value());
    }
    catch(NonEmptySurveyNewException e){
      throw new NonEmptySurveyException(_disciplineName.value(),_projectName.value());
    }
    catch(SurveyFinishedNewException e){
      throw new SurveyFinishedException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineException(_disciplineName.value());
    }
  }

}
