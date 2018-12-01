package sth.core;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Staff extends Person implements Serializable{
	private List<Discipline> _disciplines = new ArrayList<Discipline>();


	public Staff(String _name,int _phoneNumber,int _id){
		super(_name,_phoneNumber,_id);
	}
	// _______________________________________________________________________
	public void putDiscipline(Discipline d){
		_disciplines.add(d);
	}
	public List<Discipline> getDisciplines(){
		return _disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines){
		_disciplines=disciplines;
	}
	// _______________________________________________________________________


	public String show(){
		return "FUNCIONÁRIO"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
	}



}
