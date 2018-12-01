package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.DuplicateProjectException;
import sth.app.exceptions.NoSuchDisciplineException;

import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;
import sth.exceptions.newexceptions.DuplicateProjectCoreException;


/**
 * 4.3.1. Create project.
 */
public class DoCreateProject extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplineName;
  Input<String> _projectName;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
    //[FIXING-LEFT] NoSuchDisciplineException
    _projectName = _form.addStringInput(Message.requestProjectName());
    //FIXME [FIXING-END] initialize input fields if needed  }
  }
  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();
    try{
    // _display.add(_receiver.createProject(_disciplineName.value(),_projectName.value()));
    // _display.display();
    _receiver.createProject(_disciplineName.value(),_projectName.value());
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    }

    catch(DuplicateProjectCoreException e){
      throw new DuplicateProjectException(_disciplineName.value(),_projectName.value());
    }
  }

}
