package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.FinishingSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;

import sth.exceptions.newexceptions.NoSurveyNewException;
import sth.exceptions.newexceptions.FinishingSurveyNewException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;
//FIXME import other classes if needed

/**
 * 4.5.5. Finish survey.
 */
public class DoFinishSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoFinishSurvey(SchoolManager receiver) {
    super(Label.FINISH_SURVEY, receiver);
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
    // _display.add(_receiver.finishSurvey(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.finishSurvey(_disciplineName.value(),_projectName.value());
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyException(_disciplineName.value(),_projectName.value());
    }
    catch(FinishingSurveyNewException e){
      throw new FinishingSurveyException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineException(_disciplineName.value());
    }
  }

}
