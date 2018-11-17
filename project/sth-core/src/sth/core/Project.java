package sth.core;
import java.io.Serializable;

public class Project implements Serializable{
	private String _name;
	private String _description;
	private boolean _closed=false;
	private String _content;

	public Project(String name,String description,String content){
		_name=name;
		_description=description;
		_content=content;

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
	public String getDescription(){
		return _description;
	}
	public void setDescription(String description){
		_description=description;
	}
	public boolean getClosed(){
		return _closed;
	}
	public void setClosed(boolean closed){
		_closed=closed;
	}
	public String getContent(){
		return _content;
	}
	public void setContent(String content){
		_content=content;
	}

}
