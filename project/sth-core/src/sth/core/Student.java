package sth.core;
public class Student extends Person{

	private boolean _isRep=false;
	private int _numOfEnrolledDisciplines=0;

	public Student(String _name,int _phoneNumber,int _id){
		super(_name,_phoneNumber,_id);
	}

    	// To specify if hes a representative
	public Student(String _name,int _phoneNumber,int _id,boolean isRep){
		super(_name,_phoneNumber,_id);
		_isRep=isRep;
	}

	/*============================================
	=            getters and setters             =
	============================================*/

	public boolean getIsRep(){
		return _isRep;
	}
	public void setIsRep(boolean isRep){
		_isRep=isRep;
	}
	public int getNumOfEnrolledDisciplines(){
		return _numOfEnrolledDisciplines;
	}
	public void setNumOfEnrolledDisciplines(int numOfEnrolledDisciplines){
		_numOfEnrolledDisciplines=numOfEnrolledDisciplines;
	}



	// /*============================================
	public void deliverProject(Project p){

	}

	public void switchRepresentativeStatus(){
		if (_isRep=false){
			_isRep=true;
		}
		else
			_isRep=false;
	}


	public void fillSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}
	public void finalizeSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}
	public void closeSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}
	public void openSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}
	public void  cancelSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}
	public void  createSurvey(Survey s){
		// if _isRep{

		// }
		// else
		// 	throw new unauthorizedOperationException();

	}


}
