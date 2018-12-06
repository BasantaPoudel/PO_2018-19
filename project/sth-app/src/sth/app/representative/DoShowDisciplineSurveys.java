package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;


//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;
/**
 * 4.5.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoShowDisciplineSurveys(SchoolManager receiver) {
    super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
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
    // _display.add(_receiver.showDisciplineSurvey(_disciplineName.value(),_projectName.value());
    // _display.display();
    String s = _receiver.showDisciplineSurvey(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineException(_disciplineName.value());
    }
  }

}
