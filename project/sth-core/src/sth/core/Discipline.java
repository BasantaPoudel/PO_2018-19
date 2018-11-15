package sth.core;
public class Discipline{
	private String _name;
	private Course _course;

	public Discipline(Course course,String name){
		_course=course;
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

 	private String show(){
 		return "* " + _name +" - " + _course;

 	}

}
