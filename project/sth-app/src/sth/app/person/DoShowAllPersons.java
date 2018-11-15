package sth.app.person;

import pt.tecnico.po.ui.Command;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  //FIXME add input fields if needed

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {

    // _display.display(_receiver.showPersons());
    //FIXME[FIXING-BEGIN] implement command
    // for (Student s : _receiver.getPersons())
      // _display.popup(_receiver.showAllPersons());
    // _display.display();
    //FIXME[FIXING-END] implement command
  }

}
