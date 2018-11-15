package sth.app.person;

import pt.tecnico.po.ui.Command;
import sth.SchoolManager;

//FIXME [FIXING-BEGIN] import other classes if needed
import pt.tecnico.po.ui.Input;
//FIXME [FIXING-END] import other classes if needed

/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {

  //FIXME [FIXING-BEGIN] add input fields if needed
  // Input<String> _name;
  //FIXME [FIXING-END] add input fields if needed

  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    // _name = _form.addStringInput(Message.requestPersonName());
    //FIXME [FIXING-END] initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _display.add(_receiver.showPerson());
    _display.display();
  }
}
