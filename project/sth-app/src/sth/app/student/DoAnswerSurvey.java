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
 * 4.4.2. Answer survey.
 */
public class DoAnswerSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectName;
  Input<String> _disciplineName;
  Input<Float> _horas;
  Input<String> _comments;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    //FIXME initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    _projectName = _form.addStringInput(Message.requestProjectName());
    _horas = _form.addFloatInput(Message.requestProjectHours());
    _comments = _form.addStringInput(Message.requestComment());
  }


  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME [FIXING-BEGIN] implement command
  _form.parse();
  try {
    // _display.add(_receiver.answerSurvey(_disciplineName.value(),_projectName.value()));
    // _display.display();
    System.out.println(_receiver.answerSurvey(_disciplineName.value(),_projectName.value()));

  } catch (NoSuchProjectCoreException e) {
    throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());

  } catch ( NoSurveyCoreException e) {
    throw new NoSurveyException(_disciplineName.value(),_projectName.value());
  }
  catch(NoSuchDisciplineCoreException e){
    throw new NoSuchDisciplineException(_disciplineName.value());
  }
  //FIXME [FIXING-END] implement command
  }
}
