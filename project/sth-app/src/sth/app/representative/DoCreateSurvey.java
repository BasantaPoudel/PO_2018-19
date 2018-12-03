package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.DuplicateSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;

import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;
import sth.exceptions.newexceptions.DuplicateSurveyCoreException;

//FIXME import other classes if needed

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
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
    // _display.add(_receiver.createSurvey(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.createSurvey(_disciplineName.value(),_projectName.value());
    }
    catch(DuplicateSurveyCoreException e){
      throw new DuplicateSurveyException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineException(_disciplineName.value());
    }
  }

}
