package sth.core;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;

public class Project implements Serializable{
	private String _name;

	private String _description;
	private String _content;

	private boolean _closed=false;

	private Map<String,Survey> _surveys = new TreeMap<String,Survey>();

	private Map<String,ProjectSubmission> _projectSubmissions = new TreeMap<String,ProjectSubmission>();


	public Project(String name){
		_name=name;
	}


	//getters
	public String getName(){
		return _name;
	}
	public void close(){
		_closed=true;
	}
	public boolean getState(){
		return _closed;
	}
	public void open(){
        _closed=false;
	}
	public boolean hasSubmission(String projectName){
		return (_projectSubmissions.containsKey(projectName));
	}
	public void submitProject(String projectName, ProjectSubmission content){
		_projectSubmissions.put(projectName,content);
	}


}
