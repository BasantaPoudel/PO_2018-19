package sth;

//FIXME [FIXING-BEGIN] import other classes if needed
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;


import sth.exceptions.*;

import sth.core.Student;
import sth.core.Professor;
import sth.core.Discipline;
import sth.core.Person;
import sth.core.Project;
import sth.core.Staff;
import sth.core.Student;
import sth.core.Survey;
import sth.core.Course;

//FIXME [FIXING-END] import other classes if needed
/**
* School implementation.
*/
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	private int _auxId;

	//FIXME [FIXING-BEGIN] define object fields (attributes and, possibly, associations)
	private TreeMap<Integer, Student> _students = new TreeMap<Integer, Student>();
	private TreeMap<Integer, Student> _representatives = new TreeMap<Integer, Student>();
	private TreeMap<Integer, Professor> _professors = new TreeMap<Integer, Professor>();
	private TreeMap<Integer, Staff> _staffs = new TreeMap<Integer, Staff>();


	private TreeMap<Integer, Discipline> _disciplines = new TreeMap<Integer, Discipline>();


	//[FIXING-END]

	//FIXME implement constructors if needed

	/**
	* @param filename
	* @throws BadEntryException
	* @throws IOException
	*/
	/*========================================
	=            main functions
	=========================================*/

	void importFile(String filename) throws IOException, BadEntryException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;

		while ((line = reader.readLine()) != null) {
			//
			String[] fields = line.split("\\|");
			try {
				registerFromFields(fields,reader);


			} catch (UnknownDataException e) {
				System.err.printf("WARNING: unknown data");
				e.printStackTrace();
			}  catch (ClientExistsException e) {
				e.printStackTrace();
			} catch (InvalidIdentifierException e) {
				e.printStackTrace();
			}
		}
		reader.close();
	}


	// ====================================================================================


	void registerFromFields(String[] fields,BufferedReader reader) throws UnknownDataException,
	ClientExistsException,
	InvalidIdentifierException {

		Pattern pattStudent = Pattern.compile("^(ALUNO)");
		Pattern pattRepresentive  = Pattern.compile("^(DELEGADO)");
		Pattern pattProfessor = Pattern.compile("^(DOCENTE)");
		Pattern pattStaff = Pattern.compile("^(FUNCIONÁRIO)");


		if (pattStudent.matcher(fields[0]).matches()) {
			registerStudent(fields,reader);
		}
		else if (pattRepresentive .matcher(fields[0]).matches()) {
			registerRepresentive(fields,reader);
		}
		else if (pattProfessor.matcher(fields[0]).matches()) {
			registerProfessor(fields,reader);
		}
		else if (pattStaff.matcher(fields[0]).matches()) {
			registerStaff(fields,reader);
		}
		else {
			throw new UnknownDataException(fields[0]);
		}
	}




	// ====================================================================================

	/**
	*
	*
	* @param
	* @return true if next line begins with hashtag
	*/
	boolean isNextLineHashtag(BufferedReader reader) throws UnknownDataException,
	ClientExistsException, InvalidIdentifierException, IOException{
		String line;
		String[] fields;
		Pattern pattHashtag = Pattern.compile("^(#)");

		reader.mark(4096); //mark line because we just want to see and not move to next line

		line = reader.readLine(); //read (and move to) next line
		if (line != null ){
			fields = line.split("\\ "); //split by spaces
			if( pattHashtag.matcher(fields[0]).matches()){
				reader.reset(); //checking was already done
				return true;
			}
		}
		reader.reset(); //checking was already done
		return false;

	}


	// ====================================================================================
	/*========================================
	=            register functions
	=========================================*/

	/**
	* Registers using fields from already parsed line
	*
	* @param    fields
	*/
	void registerStudent(String[] fields, BufferedReader reader) throws  UnknownDataException {


		int id = Integer.parseInt(fields[1]);	_auxId=id;
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		if (fields[0].equals("ALUNO")) {
			Student student= new Student(name,phoneNumber,id);
			try{
				while(isNextLineHashtag(reader)){
					String line=reader.readLine();
					// cuts out the # part
					fields = line.split("\\#");

					//gets last 2 fields for discipline
					fields=fields[1].split("\\|");
					//makes the necessary objects for adding the discipline to student
					Course c = new Course(fields[0]);
					Discipline d = new Discipline(c,fields[1]);
					student.addDiscipline(d);
				}
			}catch(UnknownDataException e){
				// FIX
			}   		catch(IOException e){
				// FIX
			}  		catch(ClientExistsException e){
				// FIX
			}		catch(InvalidIdentifierException e){
				// FIX
			}
			addStudent(id, student);
		}
	}

	void registerRepresentive(String[] fields, BufferedReader reader) throws  UnknownDataException {

		int id = Integer.parseInt(fields[1]);	_auxId=id;
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		if (fields[0].equals("DELEGADO")) {
			Student representive= new Student(name,phoneNumber,id);
			try{
				while(isNextLineHashtag(reader)){
					String line=reader.readLine();
					// cuts out the # part
					fields = line.split("\\#");

					//gets last 2 fields for discipline
					fields=fields[1].split("\\|");
					//makes the necessary objects for adding the discipline to representive
					Course c = new Course(fields[0]);
					Discipline d = new Discipline(c,fields[1]);
					representive.addDiscipline(d);
				}
			}catch(UnknownDataException e){
				// FIX
			}   		catch(IOException e){
				// FIX
			}  		catch(ClientExistsException e){
				// FIX
			}		catch(InvalidIdentifierException e){
				// FIX
			}
			addRepresentive(id, representive);
		}
	}

	void registerProfessor(String[] fields, BufferedReader reader) throws  UnknownDataException {

		int id = Integer.parseInt(fields[1]);	_auxId=id;
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		if (fields[0].equals("DOCENTE")) {
			Professor professor= new Professor(name,phoneNumber,id);
			try{
				while(isNextLineHashtag(reader)){
					String line=reader.readLine();
					// cuts out the # part
					fields = line.split("\\#");

					//gets last 2 fields for discipline
					fields=fields[1].split("\\|");
					//makes the necessary objects for adding the discipline to professor
					Course c = new Course(fields[0]);
					Discipline d = new Discipline(c,fields[1]);
					professor.addDiscipline(d);
				}
			}catch(UnknownDataException e){
				// FIX
			}   		catch(IOException e){
				// FIX
			}  		catch(ClientExistsException e){
				// FIX
			}		catch(InvalidIdentifierException e){
				// FIX
			}
			addProfessor(id, professor);
		}
	}


	void registerStaff(String[] fields, BufferedReader reader) throws  UnknownDataException {


		int id = Integer.parseInt(fields[1]);	_auxId=id;
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		if (fields[0].equals("FUNCIONÁRIO")) {
			Staff staff= new Staff(name,phoneNumber,id);
			try{
				while(isNextLineHashtag(reader)){
					String line=reader.readLine();
					// cuts out the # part
					fields = line.split("\\#");

					//gets last 2 fields for discipline
					fields=fields[1].split("\\|");
					//makes the necessary objects for adding the discipline to staff
					Course c = new Course(fields[0]);
					Discipline d = new Discipline(c,fields[1]);
					staff.addDiscipline(d);
				}
			}catch(UnknownDataException e){
				// FIX
			}   		catch(IOException e){
				// FIX
			}  		catch(ClientExistsException e){
				// FIX
			}		catch(InvalidIdentifierException e){
				// FIX
			}
			addStaff(id, staff);
		}
	}


	/*========================================
	=            add functions
	=========================================*/


	/**
	* Adds student to proper array
	*
	* @param    id
	* @param  student
	*/
	public void addStudent(int id, Student student){
		_students.put(id,student);
	}
	public void addRepresentive (int id, Student student ){
		_representatives.put(id,student);
	}
	public void addProfessor(int id, Professor professor){
		_professors.put(id,professor);
	}

	public void addStaff(int id, Staff staff){
		_staffs.put(id,staff);
	}


	/*==========================
	=            test          =
	===========================*/
	// wash hands

	public void printStudent(){
		System.out.println("_students");
		_students.forEach((k,v)->System.out.println("name " + v.getName() + ". id " + k));
		System.out.println("_representatives");
		_representatives.forEach((k,v)->System.out.println("name " + v.getName() + ". id " + k));
		System.out.println("_professors");
		_professors.forEach((k,v)->System.out.println("name " + v.getName() + ". id " + k));
		System.out.println("_staffs");
		_staffs.forEach((k,v)->System.out.println("name " + v.getName() + ". id " + k));
	}

	public boolean hasStudent(int id){
		return _students.containsKey(id);
	}

	public boolean hasProfessor(int id){
		return _professors.containsKey(id);
	}

	public boolean hasStaff(int id){
		return _staffs.containsKey(id);
	}

	public boolean hasRepresentative(int id){
		return _representatives.containsKey(id);
	}
	public boolean hasStaffs(int id){
		return _staffs.containsKey(id);
	}


	// public String showPersons() throws UnknownAgentException{
	// 	String _allstudent = "";
	//
	// 	for (Map.Entry<Integer, Professor> entry : _professors.entrySet()) {
	// 		Professor value = entry.getValue();
	// 		String _sname =value.getName();
	// 			_allstudent += value.show();
	// 	}
	//
	// }
	public String showPerson(int id){
		Student _p1 = _students.get(id);
		Professor _p2 = _professors.get(id);
		Student _p3 = _representatives.get(id);
		Staff _p4 = _staffs.get(id);
		// if((_p1!=null) || (_p2!=null) || (_p3!=null) || (_p4!=null)){
		if(_p1!=null){
			return _p1.show(false);
		}
		else if(_p2!=null){
			return _p2.show();
		}
		else if(_p3!=null){
			return _p3.show(true);
		}
		else if(_p4!=null){
			return _p4.show();
		}
		return "";
		// return false;
	}
	//
	// }

	public String searchPerson(String name) throws UnknownAgentException{
		String _allstudent = "";

		for (Map.Entry<Integer, Professor> entry : _professors.entrySet()) {
			Professor value = entry.getValue();
			String _sname =value.getName();
			if(_sname.contains(name)){
				_allstudent += value.show();
			}
		}
		for (Map.Entry<Integer, Student> entry : _students.entrySet()) {
			Student value = entry.getValue();
			String _sname =value.getName();
			if(_sname.contains(name)){
				_allstudent += value.show(false);
			}
		}
		for (Map.Entry<Integer, Student> entry : _representatives.entrySet()) {
			Student value = entry.getValue();
			String _sname =value.getName();
			if(_sname.contains(name)){
				_allstudent += value.show(true);
			}
		}
		for (Map.Entry<Integer, Staff> entry : _staffs.entrySet()) {
			Staff value = entry.getValue();
			String _sname =value.getName();
			if(_sname.contains(name)){
				_allstudent += value.show();
			}
		}
		return _allstudent;
	}


	public void setNewPhoneNum(int id,int newTelPhone){


	   	if (_students.get(id)!=null){
	   		       Student student = _students.get(id);
	   		       student.setPhoneNumber(newTelPhone);
	   	}

	   	else if (_representatives.get(id)!=null) {
	   		         Student representative = _representatives.get(id);
	   		         representative.setPhoneNumber(newTelPhone);
	   	}

	   	else if (_professors.get(id)!=null) {
	   		         Professor professor = _professors.get(id);
	   		         professor.setPhoneNumber(newTelPhone);
	   	}

	   	else if (_staffs.get(id)!=null) {
	   		         Staff staff = _staffs.get(id);
	   		         staff.setPhoneNumber(newTelPhone);
	   	}

	   }


}
