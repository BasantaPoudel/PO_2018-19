package sth.core;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Map;
import java.util.TreeMap;

import java.io.Serializable;


public class Discipline implements Serializable{
	private String _name;
	private Course _course;

	// projectName,project
	private Map<String,Project> _projects =new TreeMap<String,Project>();
	private Map<Integer,Student> _students =new TreeMap<Integer,Student>();



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

	/*===========================================
	=            projects
	===========================================*/

	public  void addProject(Project project){
		_projects.put(project.getName(),project);
	}

	public Project getProject(String projectName){
		return _projects.get(projectName);
	}
 	/*===========================================
	=
	===========================================*/

	public void putStudent(Student student){
		_students.put(student.getId(),student);
	}
	public  Collection<Student> getStudents(){
		return _students.values();
	}


}
