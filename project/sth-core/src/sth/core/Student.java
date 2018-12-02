package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;


public class Student extends Person implements Serializable{

	private boolean _isRepresentive=false;
	private int _numOfEnrolledDisciplines=0;
	private Map<String,Discipline> _disciplines = new TreeMap<String, Discipline>();

	public Student(String _name,int _phoneNumber,int _id){
		super(_name,_phoneNumber,_id);
	}

	    	// To specify if hes a representative
	public Student(String _name,int _phoneNumber,int _id,boolean isRep){
		super(_name,_phoneNumber,_id);
		_isRepresentive=isRep;
	}

	/*============================================
	=            getters and setters             =
	============================================*/


	public void setIsRep(boolean isRep){
		_isRepresentive=isRep;
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
	public void putDiscipline(Discipline d){
		_disciplines.put(d.getName(), d);
	}



	/*=============================================

	=============================================*/

	public void deliverProject(Project p){
	}
	/*=============================================

	=============================================*/

	public void fillSurvey(Survey s){
		// FIX1
	}
	public void finalizeSurvey(Survey s){
		// FIX1
	}
	public void closeSurvey(Survey s){
		// FIX1
	}
	public void openSurvey(Survey s){
		// FIX1
	}
	public void  cancelSurvey(Survey s){
		// FIX1
	}
	public void  createSurvey(Survey s){
		// FIX1
	}

	/*=============================================

	=============================================*/


	/**
 * Show info about this student
 *
 *
 * @return  // FIX
 */
	public String show(){
		String res;
		if (_isRepresentive)
			res= "DELEGADO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
		else
			res= "ALUNO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

		for(Map.Entry<String,Discipline> entr : _disciplines.entrySet()) {
			Discipline discipline = entr.getValue();
  			res=res+"*" + discipline.getCourseName()+" - "+discipline.getName()+"\n";
		}

		return res;

	}

	public String showWithDisciplines(){
		String res;
		if (_isRepresentive)
			res= "DELEGADO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
		else
			res= "ALUNO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

            for (String disciplineName : _disciplines.keySet() ){

				Discipline d=_disciplines.get(disciplineName);
				String courseName=d.getCourseName();

				res=res+"*" + courseName +" - "+disciplineName+"\n";
            }
        return res;
    }



}
