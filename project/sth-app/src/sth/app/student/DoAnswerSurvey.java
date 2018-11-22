package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.4.2. Answer survey.
 */
public class DoAnswerSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectname;
  Input<String> _disciplinename;
  Input<Float> _horas;
  Input<String> _comments;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
    _horas = _form.addFloatInput(Message.requestProjectHours());
    _comments = _form.addStringInput(Message.requestComment());

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME [FIXING-BEGIN] implement command
  _form.parse();
  // try {
  //   _display.add(_receiver.answerSurvey();
  //   _display.display();
  //
  // } catch (NoSuchProjectException e) {
  //   throw new NoSuchProjectException(_projectname);
  //
  // } catch ( NoSurveyException e) {
  //   throw new NoSurveyException(_disciplinename,_projectname);
  // }
  // catch(NoSuchDisciplineException e){
  //   throw new NoSuchDisciplineException(_disciplinename);
  //
  }
  //FIXME [FIXING-END] implement command

}
