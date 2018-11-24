package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class Professor extends Person implements Serializable{


  private TreeMap<String,	TreeMap<String,	Discipline>> _disciplines = new TreeMap<String,	TreeMap<String,	Discipline>>();

  public Professor(String _name,int _phoneNumber,int _id){
    super(_name,_phoneNumber,_id);
  }

	public void setName(String name){
		super.setName(name);
	}
	public int getPhoneNumber(){
		return super.getPhoneNumber();
	}
	public void setPhoneNumber(int phoneNumber){
		super.setPhoneNumber(phoneNumber);
	}

	public int getId(){
		return super.getId();
	}
	public void setId(int id){
		super.setId(id);
	}
	/*=====  End of getters and setters  ======*/

	// _______________________________________________________________________
	public void addDiscipline(Discipline discipline){

    // names
		String courseName=discipline.getCourseName();
    String disciplineName=discipline.getName();

    if (_disciplines.get(courseName) == null){

      TreeMap<String,Discipline> disciplinesMap = new TreeMap<String,Discipline>();  // new Map

      disciplinesMap.put(disciplineName,discipline);
      _disciplines.put(courseName,disciplinesMap);

    }
    else if (_disciplines.get(courseName) != null){

      _disciplines.get(courseName).put(disciplineName,discipline); // existing Map

    }
}
//
	// _______________________________________________________________________

	public void createProject(String proj,String disc){
	}
	public void closeProject(String proj,String disc){
	}

	public String show(){

		return "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

	}

	public String showWithDisciplines(){

		String res = "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
    for (String courseName : _disciplines.keySet() ) {
      for (String disciplineName : _disciplines.get(courseName).keySet() ){
      	res=res+"*" + courseName +" - "+disciplineName+"\n";
      }
    }
    return res;
  }




}
