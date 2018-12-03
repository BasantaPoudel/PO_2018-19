package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME [FIXING-BEGIN] import other classes if needed
import pt.tecnico.po.ui.Input;
//FIXME [FIXING-END] import other classes if needed
/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {


    //FIXME[FIXING-BEGIN] add input fields if needed
    Input<Integer> _newNumber;

    //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);
    //FIXME initialize input fields if needed
    _newNumber = _form.addIntegerInput(Message.requestPhoneNumber());

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
       //   //FIXME [FIXING-BEGIN] implement command
       _form.parse();
       _display.add(_receiver.setNewPhoneNum(_newNumber.value()));
       _display.display();
  }

}
