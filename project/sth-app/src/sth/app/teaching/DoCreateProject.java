package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.DuplicateProjectException;
import sth.app.exceptions.NoSuchDisciplineException;

import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;
import sth.exceptions.newexceptions.DuplicateProjectexcepcao;


/**
 * 4.3.1. Create project.
 */
public class DoCreateProject extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplinename;
  Input<String> _projectname;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    //[FIXING-LEFT] NoSuchDisciplineException
    _projectname = _form.addStringInput(Message.requestProjectName());
    //FIXME [FIXING-END] initialize input fields if needed  }
  }
  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();
    try{
    // _display.add(_receiver.createProject(_disciplinename.value(),_projectname.value()));
    // _display.display();
    System.out.println(_receiver.createProject(_disciplinename.value(),_projectname.value()));
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());
    }

    catch(DuplicateProjectexcepcao e){
      throw new DuplicateProjectException(_disciplinename.value(),_projectname.value());
    }
  }

}
