package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class Professor extends Person implements Serializable{


	private Map<String,Discipline> _disciplines = new TreeMap<String, Discipline>();

	public Professor(String name,int phoneNumber,int id){
		super(name,phoneNumber,id);
	}

	/*===========================================
	=            getters and setters            =
	===========================================*/
	public String getName(){
		return super.getName();
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
	public void addDiscipline(Discipline d){
		_disciplines.put(d.getName(), d);
	}

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
		for(Map.Entry<String,Discipline> entr : _disciplines.entrySet()) {
			Discipline discipline = entr.getValue();
  			res=res+"*" + discipline.getCourse().getName()+" - "+discipline.getName()+"\n";
		}
		return res;
	}



}
