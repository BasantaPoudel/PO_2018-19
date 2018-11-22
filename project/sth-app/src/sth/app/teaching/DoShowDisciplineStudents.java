package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.3.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplinename;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();
    // if(_receiver.existsDiscipline==true){
    // _display.add(_receiver.showDisciplineStudents()));
    // _display.display();
    // }

    // try{
    // _display.add(_receiver.showDisciplineStudents()));
    // _display.display();
    // }
    // catch(NoSuchProjectException e){
    //   throw new NoSuchProjectException(_projectname);
    // }
    // }
    // catch(NoSuchDisciplineException e){
    //   throw new NoSuchDisciplineException(_disciplinename);
    // }

  }

}
