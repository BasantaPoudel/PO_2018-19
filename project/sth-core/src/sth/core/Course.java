package sth.core;
import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;

public class Course implements Serializable{
	private String _name;
	private int _numOfRepresentatives=0;
	private Map<String,Discipline> _disciplines = new TreeMap<String,Discipline>();

	public Course(String name){
		_name=name;
	}


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
		// if (!hasDiscipline(d)){
			_disciplines.put(d.getName(),d);
		// }
	}
	public boolean hasDiscipline(String disciplineName){
		return _disciplines.containsKey(disciplineName);
	}
	public Discipline getDiscipline(String disciplineName){
		if (_disciplines.containsKey(disciplineName)){
			return _disciplines.get(disciplineName);
		}
		else
			return null;
	}

}
