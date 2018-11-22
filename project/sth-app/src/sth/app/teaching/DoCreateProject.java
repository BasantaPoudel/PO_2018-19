package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.3.1. Create project.
 */
public class DoCreateProject extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectname;
  Input<String> _disciplinename;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
    //FIXME [FIXING-END] initialize input fields if needed  }
  }
  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    //FIXME implement command
  }

}
