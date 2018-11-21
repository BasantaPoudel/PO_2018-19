package sth.core;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;


public class Discipline implements Serializable{
	private String _name;
	private Course _course;
	private List<Integer> _studentIDs= new ArrayList<Integer>();




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

	public Course getCourse(){
		return _course;
	}
	public void setCourse(Course course){
		_course=course;
	}
 


	/*=============================================
	=            map operations
	=============================================*/
	public void addID(int id){
		_studentIDs.add(id);
	}
	public List<Integer> getIDs(){
		return _studentIDs;
	}

	public void setIDs(List<Integer> ids){
		_studentIDs=ids;
	}


	

}
