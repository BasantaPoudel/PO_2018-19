package sth.core;
import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;

public class Survey implements Serializable{
	private double _hoursSpent;
	private String  _freeComments;
	private boolean _closed=false;
	private boolean _finalized=false;

	// private SurveyState _state = new SurveyCreated(this);
	// private Map<String,SurveySubmission> _surveySubmissions = new TreeMap<String,SurveySubmission>();

	public Survey(double hoursSpent ,String freeComments){
		_hoursSpent=hoursSpent;
		_freeComments=freeComments;

	}

	// public void setState(SurveyState state) {
	// 	_state = state;
	// }

	/*============================================
	=            getters and setters             =
	============================================*/
	public double getHoursSpent(){
		return _hoursSpent;
	}
	public void setHoursSpent(double hoursSpent){
		_hoursSpent=hoursSpent;
	}
	public String  getFreeComments(){
		return _freeComments;
	}
	public void setFreeComments(String  freeComments){
		_freeComments=freeComments;
	}
	public boolean getClosed(){
		return _closed;
	}
	public void setClosed(boolean closed){
		_closed=closed;
	}
	public boolean getFinalized(){
		return _finalized;
	}
	public void setFinalized(boolean finalized){
		_finalized=finalized;
	}


	/*============================================
	=           Other Methods             =
	============================================*/

	public void cancel(){
		// _state.cancel();
	}
	public void open(){
		// _state.open();
	}
	public void close(){
		// _state.close();
	}
	public void finalise(){
		// _state.finalise();
	}
	public void submit(){
		// _state.submit();
	}

}
