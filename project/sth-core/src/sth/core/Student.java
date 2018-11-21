package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;


public class Student extends Person implements Serializable{

	private boolean _isRep=false;
	private int _numOfEnrolledDisciplines=0;
	private Map<String,Discipline> _disciplines = new TreeMap<String, Discipline>();

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
		_disciplines.put(d.getName(), d);
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


		for(Map.Entry<String,Discipline> entr : _disciplines.entrySet()) {
			Discipline discipline = entr.getValue();
  			res=res+"*" + discipline.getCourse().getName()+" - "+discipline.getName()+"\n";
		}
		
		return res;

	}



}
