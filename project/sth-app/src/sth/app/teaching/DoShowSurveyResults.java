package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSurveyException;


import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;
import sth.exceptions.newexceptions.NoSurveyexcepcao;


/**
 * 4.3.5. Show survey results.
 */
public class DoShowSurveyResults extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _disciplinename;
  Input<String> _projectname;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
    _form.parse();


    try{
    // _display.add(_receiver.showSurveyResults(_disciplinename.value(),_projectname.value()));
    // _display.display();
    System.out.println(_receiver.showSurveyResults(_disciplinename.value(),_projectname.value()));
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());
    }

    catch(NoSurveyexcepcao e){
      throw new NoSurveyException(_disciplinename.value(),_projectname.value());
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineException(_disciplinename.value());
    }

  }

}
