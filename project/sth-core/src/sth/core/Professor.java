package sth.core;
import java.util.*;

public class Professor extends Person{


	private List<Discipline> _disciplines = new ArrayList<Discipline>();

	public Professor(String _name,int _phoneNumber,int _id){
		super(_name,_phoneNumber,_id);
	}

 
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


}
