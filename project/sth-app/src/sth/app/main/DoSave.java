package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

// FIXME import other classes if needed

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
      if(_receiver.getInitial()==true){
        if(_receiver.getChanged()==false){
          // System.out.println(_receiver.getChanged());
          // System.out.println(_receiver.getInitial());
          // _receiver.setInitial();
          _fileName = _form.addStringInput(Message.newSaveAs());
          _form.parse();
          _receiver.doSave(_fileName.value());
        }
        else{
          // _fileName = _form.addStringInput(Message.saveAs());
          // _form.parse();
          _receiver.doSave(_fileName.value());
        }
      }
    }
  }
