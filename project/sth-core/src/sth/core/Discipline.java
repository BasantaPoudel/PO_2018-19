package sth.core;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import java.util.Map;
import java.util.TreeMap;

import java.io.Serializable;


public class Discipline implements Serializable{
	private String _name;
	private Course _course;

	// projName,project
	private Map<String,Project> _projects =new TreeMap<String,Project>();



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

	public String getCourseName(){
		return _course.getName();
	}
	public void setCourse(Course course){
		_course=course;
	}


 




}
