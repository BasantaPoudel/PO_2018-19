package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;


//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;
/**
 * 4.5.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectname;
  Input<String> _disciplinename;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoShowDisciplineSurveys(SchoolManager receiver) {
    super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();

    try{
    // _display.add(_receiver.showDisciplineSurvey(_disciplinename.value(),_projectname.value()));
    // _display.display();
    System.out.println(_receiver.showDisciplineSurvey(_disciplinename.value(),_projectname.value()));
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineException(_disciplinename.value());
    }
  }

}
