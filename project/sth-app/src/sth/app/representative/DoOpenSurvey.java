package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.OpeningSurveyException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;

import sth.exceptions.newexceptions.NoSurveyexcepcao;
import sth.exceptions.newexceptions.OpeningSurveyexcepcao;
import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;

//FIXME import other classes if needed

/**
 * 4.5.3. Open survey.
 */
public class DoOpenSurvey extends Command<SchoolManager> {

  //FIXME[FIXING-BEGIN] add input fields if needed
  Input<String> _projectname;
  Input<String> _disciplinename;
  //FIXME[FIXING-END] add input fields if needed
  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
    //FIXME initialize input fields if needed
    _disciplinename = _form.addStringInput(Message.requestDisciplineName());
    _projectname = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    // _display.add(_receiver.doOpennnn(_projectname.value())));
    // _display.display();
    try{
    _display.add(_receiver.openSurvey(_disciplinename.value(),_projectname.value()));
    _display.display();
    }
    catch(NoSurveyexcepcao e){
      throw new NoSurveyException(_disciplinename.value(),_projectname.value());
    }
     catch(OpeningSurveyexcepcao e){
       throw new OpeningSurveyException(_disciplinename.value(),_projectname.value());
     }
     catch(NoSuchProjectexcepcao e){
       throw new NoSuchProjectException(_disciplinename.value(),_projectname.value());
     }
     catch(NoSuchDisciplineexcepcao e){
       throw new NoSuchDisciplineException(_disciplinename.value());
     }
  }

}
