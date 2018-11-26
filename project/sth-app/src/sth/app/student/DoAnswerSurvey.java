package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.exceptions.newexceptions.NoSurveyexcepcao;
import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;

import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;
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
  try {
    // _display.add(_receiver.answerSurvey(_disciplinename.value(),_projectname.value()));
    // _display.display();
    System.out.println(_receiver.answerSurvey(_disciplinename.value(),_projectname.value()));

  } catch (NoSuchProjectexcepcao e) {
    throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());

  } catch ( NoSurveyexcepcao e) {
    throw new NoSurveyException(_disciplinename.value(),_projectname.value());
  }
  catch(NoSuchDisciplineexcepcao e){
    throw new NoSuchDisciplineException(_disciplinename.value());
  }
  //FIXME [FIXING-END] implement command
  }
}
