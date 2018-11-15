package sth.core;
import java.util.*;

public class Professor extends Person{


	private List<Discipline> _disciplines = new ArrayList<Discipline>();

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
		_disciplines.add(d);
	}
	public List<Discipline> getDisciplines(){
		return _disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines){
		_disciplines=disciplines;
	}
	// _______________________________________________________________________

	public void createProject(String proj,String disc){
	}
	public void closeProject(String proj,String disc){
	}

	public String show(){

		return "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName();

	}

	public String showWithDisciplines(){

		String res = "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName();
		for (Discipline discipline : _disciplines) {
			res=res+"\n*" + discipline.getCourse().getName()+" - "+discipline.getName();
		}
		return res;

	}



}
