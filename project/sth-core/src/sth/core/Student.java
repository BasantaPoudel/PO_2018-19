package sth.core;
import java.util.*;

public class Student extends Person{

	private boolean _isRep=false;
	private int _numOfEnrolledDisciplines=0;

	public Student(String _name,int _phoneNumber,int _id){
		super(_name,_phoneNumber,_id);
	}
	private List<Discipline> _disciplines = new ArrayList<Discipline>();

	    	// To specify if hes a representative
	public Student(String _name,int _phoneNumber,int _id,boolean isRep){
		super(_name,_phoneNumber,_id);
		_isRep=isRep;
	}

	/*============================================
	=            getters and setters             =
	============================================*/
	
	// _______________________________________________________________________
	public void addDiscipline(Discipline d){
		_disciplines.add(d);
	}
	public List<Discipline> getDisciplines(){
		return _disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines){
		_disciplines=disciplines;
	}
	// _______________________________________________________________________

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

	/*====================END========================*/


	public void deliverProject(Project p){
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

	/**
 * Show info about this student 
 *
 * @param   isRepresentative	Whether student is representative or not (School class is reponsible for knowing)
 * @return  //FIX
 */
	public String show(boolean isRepresentative){
		if (isRepresentative)
			return "DELEGADO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName();
		else 
			return "ALUNO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName();
	}

	

}
