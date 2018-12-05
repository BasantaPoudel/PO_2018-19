package sth.core;
import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;
public class Course implements Serializable{
	private String _name;
	private int _numOfRepresentatives=0;

	public Course(String name){
		_name=name;
	}

	private Map <String,Discipline> _disciplines= new TreeMap<String,Discipline>();

	/*===========================================
	=            getters and setters            =
	===========================================*/

	public String getName(){
		return _name;
	}
	public void setName(String name){
		_name=name;
	}
	public int getNumOfRepresentatives(){
		return _numOfRepresentatives;
	}
	public void setNumOfRepresentatives(int numOfRepresentatives){
		_numOfRepresentatives=numOfRepresentatives;
	}

	public void putDiscipline(Discipline d){
		if (!hasDiscipline(d)){
			_disciplines.put(d.getName(),d);
		}
	}
	public boolean hasDiscipline(Discipline d){
		return _disciplines.containsKey(d.getName());
	}
	public Discipline getDiscipline(String dname){
		return _disciplines.get(dname);
	}
}
