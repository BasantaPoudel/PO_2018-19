package sth.core;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Project implements Serializable{
	private String _name;
	private String _description;
	private boolean _closed=false;
	private String _content;

	private TreeMap<String,Survey> _surveys = new TreeMap<String,Survey>();


	public Project(String name,String description,String content){
		_name=name;
		_description=description;
		_content=content;

	}

	void close(){
		_closed=false;
	}
	void open(){

	}


}
