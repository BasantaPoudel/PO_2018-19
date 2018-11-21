package sth.core;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;


public class Student extends Person implements Serializable{

	private boolean _isRep=false;
	private int _numOfEnrolledDisciplines=0;
	private List<Discipline> _disciplines = new ArrayList<Discipline>();

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

	/*=============================================
	=            map operations
	=============================================*/
	public void addDiscipline(Discipline d){
		_disciplines.add(d);
	}
	public List<Discipline> getDisciplines(){
		return _disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines){
		_disciplines=disciplines;
	}



	/*=============================================
		
	=============================================*/



	public void deliverProject(Project p){
	}
	public void fillSurvey(Survey s){
		//FIX1
	}
	public void finalizeSurvey(Survey s){
		//FIX1
	}
	public void closeSurvey(Survey s){
		//FIX1
	}
	public void openSurvey(Survey s){
		//FIX1
	}
	public void  cancelSurvey(Survey s){
		//FIX1
	}
	public void  createSurvey(Survey s){
		//FIX1
	}



	/**
 * Show info about this student
 *
 * @param   isRepresentative	Whether student is representative or not (School class is reponsible for knowing)
 * @return  //FIX
 */
	public String show(boolean isRepresentative){
		String res;
		if (isRepresentative)
			res= "DELEGADO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
		else
			res= "ALUNO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

		for (Discipline discipline : _disciplines) {
			res=res+"*" + discipline.getCourse().getName()+" - "+discipline.getName()+"\n";
		}
		return res;

	}



}
