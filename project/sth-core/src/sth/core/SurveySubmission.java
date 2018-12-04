package sth.core;
import java.io.Serializable;

public class SurveySubmission implements Serializable{
	private double _hoursSpent;
	private String  _freeComment;
	private boolean _closed=false;
	private boolean _finalized=false;


	public SurveySubmission(double hoursSpent ,String freeComment){
		_hoursSpent=hoursSpent;
		_freeComment=freeComment;
	}

	/*============================================
	=            getters and setters             =
	============================================*/
	public double getHoursSpent(){
		return _hoursSpent;
	}
	public void setHoursSpent(double hoursSpent){
		_hoursSpent=hoursSpent;
	}
	public String  getfreeComment(){
		return _freeComment;
	}
	public void setfreeComment(String  freeComment){
		_freeComment=freeComment;
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
}
