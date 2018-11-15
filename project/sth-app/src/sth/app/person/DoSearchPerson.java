package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME [FIXING-BEGIN] import other classes if needed
import pt.tecnico.po.ui.Input;
import sth.exceptions.UnknownAgentException;
//FIXME [FIXING-END] import other classes if needed

/**
* 4.2.4. Search person.
*/
public class DoSearchPerson extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _name;

  //FIXME[FIXING-END] add input fields if needed

  /**
  * @param receiver
  */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
    //FIXME [FIXING-BEGIN] initialize input fields if needed
    _name = _form.addStringInput(Message.requestPersonName());
    //FIXME [FIXING-END] initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    //   //FIXME [FIXING-BEGIN] implement command
    //   // _display.popup(_receiver.showPerson()); //[Debug]
    _form.parse();
    try {
      // _display.display(_receiver.searchPerson(_name.value()));
      _receiver.searchPerson(_name.value());
      // System.out.println(_receiver.searchPerson(_name.value()));

    } catch (UnknownAgentException e) {
      System.err.printf("WARNING: unknown agent");
      // 	throw new UnknownAgentException();
    }
    //   //FIXME [FIXING-END] implement command
  }


}
