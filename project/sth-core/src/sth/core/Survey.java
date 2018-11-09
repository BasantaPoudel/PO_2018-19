public class Survey{
	private double _hoursSpent;
	private String  _freeComments;
	private boolean _closed=false;
	private boolean _finalized=false;




	public Survey(double hoursSpent ,String freeComments){
		_hoursSpent=hoursSpent;
		_freeComments=freeComments;
 
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
}