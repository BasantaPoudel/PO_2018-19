package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.app.exceptions.NoSuchDisciplineException;

import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;


/**
* 4.3.3. Show project submissions.
*/
public class DoShowProjectSubmissions extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplinename;
  Input<String> _projectname;
  //FIXME[FIXING-END] add input fields if needed
  /**
  * @param receiver
  */
  public DoShowProjectSubmissions(SchoolManager receiver) {
    super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
    //FIXME [FIXING-END] initialize input fields if needed  }
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();

    try{
    // _display.add(_receiver.showProjectSubmissions(_disciplinename.value(),_projectname.value()));
    // _display.display();
    System.out.println(_receiver.showProjectSubmissions(_disciplinename.value(),_projectname.value()));
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());
    }

    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineException(_disciplinename.value());
    }
  }

}
