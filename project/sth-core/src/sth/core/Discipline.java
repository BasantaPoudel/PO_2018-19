package sth.core;
public class Discipline{
	private String _name;
	private Course _course;

	public Discipline(String name,Course course){
		_name=name;
		_course=course;
	
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

 	private String show(){
 		return "* " + _name +" - " + _course;

 	}

}
