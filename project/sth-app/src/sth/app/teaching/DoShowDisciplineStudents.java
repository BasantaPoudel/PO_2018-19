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
 * 4.3.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplineName;
  //FIXME[FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    //FIXME initialize input fields if needed
    _disciplineName = _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();
    // if(_receiver.existsDiscipline(_disciplineName.value())==true){
    // _display.add(_receiver.showDisciplineStudents(_disciplineName.value()));
    // _display.display();
    // }

    try{
    _display.add(_receiver.showDisciplineStudents(_disciplineName.value()));
    _display.display();
    // String s = _receiver.showDisciplineStudents(_disciplineName.value());
    }
    // catch(NoSuchProjectCoreException e){
    //   throw new NoSuchProjectException(_disciplineName.value(),_projectName.value());
    // }

    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineException(_disciplineName.value());
    }

  }

}
