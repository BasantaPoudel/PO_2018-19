package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

  //FIXME add input fields if needed

  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
  	 //    _newTelPhone = _form.addStringInput(Message.requestPhoneNumber());
  	 //    _receiver


  	 //     //   //FIXME [FIXING-BEGIN] implement command
    // //   // _display.popup(_receiver.showPerson()); //[Debug]
    // _form.parse();
    // try {
    //   _display.add(_receiver.searchPerson(_name.value()));
    //   _display.display();
    //   // _receiver.searchPerson(_name.value());
    //   // System.out.println(_receiver.searchPerson(_name.value()));
    // } catch (UnknownAgentException e) {
    //   System.err.printf("WARNING: unknown agent");
    //   // 	throw new UnknownAgentException();
    // }
  

  }

}
