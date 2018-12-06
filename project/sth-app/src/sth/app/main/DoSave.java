package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
* 4.1.1. Save to file under current name (if unnamed, query for name).
*/
public class DoSave extends Command<SchoolManager> {



  Input<String> _fileName;


  /**
  * @param receiver
  */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
  }
    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
      if(_receiver.getInitial()==true && _receiver.getIsLoaded()==false){
          _fileName = _form.addStringInput(Message.newSaveAs());
          _form.parse();
          _receiver.doSave(_fileName.value());
      }
      else if(_receiver.getChanged()==true && _receiver.getInitial()==true && _receiver.getIsLoaded()==false){
          _fileName = _form.addStringInput(Message.newSaveAs());
          _form.parse();
          _receiver.doSave(_fileName.value());
      }
      else if(_receiver.getIsLoaded()==false){
        // _fileName = _form.addStringInput(Message.newSaveAs());
        // _form.parse();
        _receiver.doSave(_fileName.value());
      }
      else{
        _receiver.doSave();
      }
    }
  }
