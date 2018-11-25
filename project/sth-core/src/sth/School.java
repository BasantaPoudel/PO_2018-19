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

import sth.exceptions.BadEntryException;
import sth.exceptions.UnknownDataException;
import sth.exceptions.UnknownAgentException;
import sth.exceptions.InvalidIdentifierException;
import sth.exceptions.ClientExistsException;

import sth.core.Student;
import sth.core.Professor;
import sth.core.Discipline;
import sth.core.Person;
import sth.core.Project;
import sth.core.Staff;
import sth.core.Student;
import sth.core.Survey;
import sth.core.Course;


import sth.exceptions.newexceptions.NonEmptySurveyexcepcao;
import sth.exceptions.newexceptions.OpeningSurveyexcepcao;
import sth.exceptions.newexceptions.ClosingSurveyexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;
import sth.exceptions.newexceptions.NoSurveyexcepcao;
import sth.exceptions.newexceptions.FinishingSurveyexcepcao;
import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.DuplicateSurveyexcepcao;
import sth.exceptions.newexceptions.SurveyFinishedexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;



//FIXME [FIXING-END] import other classes if needed

/**
* School implementation.
*/
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;




	// maps for people and theirs ids
	private Map<Integer, Student> _students = new TreeMap<Integer, Student>();
	private Map<Integer, Student> _representatives = new TreeMap<Integer, Student>();
	private Map<Integer, Professor> _professors = new TreeMap<Integer, Professor>();
	private Map<Integer, Staff> _staffs = new TreeMap<Integer, Staff>();
	//all people
	private Map<Integer, Person> _persons= new TreeMap<Integer, Person>();



	//[FIXING-END]

	//FIXME implement constructors if needed

	/*========================================
	=            main functions
	=========================================*/

	/**
	* @param filename
	* @throws BadEntryException
	* @throws IOException
	*/
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

	/**
	* Does the Pattern Matching with the fields parsed and calls the corresponding method to register each Object.
	*
	* @param fields
	*				 				Contains List of Strings splitted.
	* @param reader
	*								BufferedReader
	* @throws UnknownDataException
	* @throws ClientExistsException
	* @throws InvalidIdentifierException
	*
	*/

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
	* Checks if the next line to read in the inputfile has #.
	*
	* @param reader
	*								BufferedReader
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
	* @param    reader
	* @throws  UnknownDataException
	*/
	void registerStudent(String[] fields, BufferedReader reader) throws  UnknownDataException {

		// vars
		int id = Integer.parseInt(fields[1]);
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		if (fields[0].equals("ALUNO")) {
			Student student= new Student(name,phoneNumber,id);
			try{
				while(isNextLineHashtag(reader)){
					String line=reader.readLine();
					// for each # line we will:

					// cut out the # part
					fields = line.split("\\#");

					//gets last 2 fields for discipline
					fields=fields[1].split("\\|");

					//make the necessary objects for adding the discipline to student
					Course c = new Course(fields[0]);



					Discipline disc = new Discipline(c,fields[1]);
					// disc.addStudentID()
					disc.addStudentID(id);
					student.addDiscipline(disc);


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
			addPerson(id, student);
		}
	}

	/**
	*Registers Representatives from fields from already parsed line
	*
	* @param fields
	*
	* @param reader BufferedReader
	* @throws UnknownDataException
	*
	*/
	void registerRepresentive(String[] fields, BufferedReader reader) throws  UnknownDataException {

		int id = Integer.parseInt(fields[1]);
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
					Discipline disc = new Discipline(c,fields[1]);
					representive.addDiscipline(disc);
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
			addPerson(id, representive);
		}
	}


	/**
	*Registers Professors from fields from already parsed line
	*
	* @param fields
	* @param reader BufferedReader
	* @throws UnknownDataException
	*/
	void registerProfessor(String[] fields, BufferedReader reader) throws  UnknownDataException {

		int id = Integer.parseInt(fields[1]);
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
					Discipline disc = new Discipline(c,fields[1]);
					professor.addDiscipline(disc);
				}
				professor.TreeToHashMap(); //now we have a non transient map in professor
			}
			catch(UnknownDataException e){
				// FIX
			}   		catch(IOException e){
				// FIX
			}  		catch(ClientExistsException e){
				// FIX
			}		catch(InvalidIdentifierException e){
				// FIX
			}
			addProfessor(id, professor);
			addPerson(id, professor);
		}
	}


	/**
	*Registers Staffs from fields from already parsed line
	*
	* @param   fields
	* @param   reader BufferedReader
	* @throws  UnknownDataException
	*/
	void registerStaff(String[] fields, BufferedReader reader) throws  UnknownDataException {


		int id = Integer.parseInt(fields[1]);
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
					Discipline disc = new Discipline(c,fields[1]);
					staff.addDiscipline(disc);
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
			addPerson(id, staff);
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

	/**
	* Adds representive to proper array
	*
	* @param id int
	* @param student Student
	*/
	public void addRepresentive (int id, Student student ){
		_representatives.put(id,student);
	}

	/**
	* Adds professor to proper array
	*
	* @param id int
	* @param professor Professor
	*/

	public void addProfessor(int id, Professor professor){
		_professors.put(id,professor);
	}

	/**
	* Adds staff to proper array
	*
	* @param id int
	* @param staff Staff
	*/

	public void addStaff(int id, Staff staff){
		_staffs.put(id,staff);
	}

	/**
	* Adds person to proper array
	*
	* @param id int
	* @param person Person
	*/
	public void addPerson(int id, Person person){
		_persons.put(id,person);
	}


	// for tests

	// public void printStudent(){
	// 	System.out.println("_students");
	// 	_students.forEach((k,v)->System.out.println(v.getId()+"|"+v.getPhoneNumber()+"|"+v.getName()));
	// 	System.out.println("_representatives");
	// 	_representatives.forEach((k,v)->System.out.println(v.getId()+"|"+v.getPhoneNumber()+"|"+v.getName()));
	// 	System.out.println("_professors");
	// 	_professors.forEach((k,v)->System.out.println(v.getId()+"|"+v.getPhoneNumber()+"|"+v.getName()));
	// 	System.out.println("_staffs");
	// 	_staffs.forEach((k,v)->System.out.println(v.getId()+"|"+v.getPhoneNumber()+"|"+v.getName()));
	//
	// 	System.out.println("_persons");
	// 	_persons.forEach((k,v)->System.out.println(v.getId()+"|"+v.getPhoneNumber()+"|"+v.getName()));
	// }

	/*=====================================
	=            has functions            =
	=====================================*/



	/**
	*
	*
	* @param loginID int
	* @return true if the Map storing students finds a student with the given id in the Map.
	*/

	public boolean hasStudent(int loginID){
		return _students.containsKey(loginID);
	}

	/**
	*
	*
	* @param loginID int
	* @return true if the Map storing Professors finds a professor with the given loginID in the Map.
	*/

	public boolean hasProfessor(int loginID){
		return _professors.containsKey(loginID);
	}

	/**
	*
	*
	* @param loginID int
	* @return true if the Map storing Staffs finds a staff with the given loginID in the Map.
	*/

	public boolean hasStaff(int loginID){
		return _staffs.containsKey(loginID);
	}

	/**
	*
	*
	* @param loginID int
	* @return true if the Map storing Representatives finds a representative with the given loginID in the Map.
	*/

	public boolean hasRepresentative(int loginID){
		return _representatives.containsKey(loginID);
	}
	/**
	*
	*
	* @param loginID int
	* @return true if the Map storing Staffs finds a staff with the given loginID in the Map.
	*/

	public boolean hasStaffs(int loginID){
		return _staffs.containsKey(loginID);
	}

	/**
	*Performs the Command DoShowAllPersons
	*
	* @return String
	*/

	// public String showAllPersons(){
	// String _allpersons = "";
	// for (Map.Entry<Integer, Professor> entry : _professors.entrySet()) {
	// 	Professor value = entry.getValue();
	// 	String _sname =value.getName();
	// 	_allpersons += value.show();
	// }
	// for (Map.Entry<Integer, Student> entry : _students.entrySet()) {
	// 	Student value = entry.getValue();
	// 	String _sname =value.getName();
	// 	_allpersons += value.show(false);
	// }
	// for (Map.Entry<Integer, Student> entry : _representatives.entrySet()) {
	// 	Student value = entry.getValue();
	// 	String _sname =value.getName();
	// 	_allpersons += value.show(true);
	// }
	// for (Map.Entry<Integer, Staff> entry : _staffs.entrySet()) {
	// 	Staff value = entry.getValue();
	// 	String _sname =value.getName();
	// 	_allpersons += value.show();
	// }
	//}


	/*=====================================
	=   Metodos de Portal Pessoal         =
	=====================================*/

	/**
	*Performs the Command DoShowPerson
	*
	* @param   loginID int
	* @return String
	*/

	//3.1
	public String showPerson(int loginID){
		Student _pStudent = _students.get(loginID);
		Professor _pProf = _professors.get(loginID);
		Student _pRepr = _representatives.get(loginID);
		Staff _pStaff = _staffs.get(loginID);
		if(_pStudent!=null){
			return _pStudent.show(false);
		}
		else if(_pProf!=null){
			return _pProf.showWithDisciplines();
		}
		else if(_pRepr!=null){
			return _pRepr.show(true);
		}
		else if(_pStaff!=null){
			return _pStaff.show();
		}
		return "";
	}

	/**
	*Perform the Command DoChangePhoneNumber
	*
	* @param   loginID loginID iof the person to change the number
	* @param   newTelPhone new telephone number to set in the Map.
	* @return String
	*/

	//3.2
	public String setNewPhoneNum(int loginID,int newTelPhone){
		if (_students.get(loginID)!=null){
			Student student = _students.get(loginID);
			Person globalStudent = _persons.get(loginID);
			student.setPhoneNumber(newTelPhone);
			globalStudent.setPhoneNumber(newTelPhone);
		}

		else if (_representatives.get(loginID)!=null) {
			Student representative = _representatives.get(loginID);
			Person globalRepresentative = _persons.get(loginID);
			representative.setPhoneNumber(newTelPhone);
			globalRepresentative.setPhoneNumber(newTelPhone);

		}

		else if (_professors.get(loginID)!=null) {
			Professor professor = _professors.get(loginID);
			Person globalProfessor = _persons.get(loginID);
			professor.setPhoneNumber(newTelPhone);
			globalProfessor.setPhoneNumber(newTelPhone);
		}

		else if (_staffs.get(loginID)!=null) {
			Staff staff = _staffs.get(loginID);
			Person globalStaff = _persons.get(loginID);
			staff.setPhoneNumber(newTelPhone);
			globalStaff.setPhoneNumber(newTelPhone);
		}

		return showPerson(loginID);
		// printStudent();
	}

	//3.3
	public String showAllPersons(){
		String _allpersons = "";


		for (Map.Entry<Integer, Person> entry : _persons.entrySet()) {
			Person value = entry.getValue();

			if(value instanceof Student){
				Student student = (Student) value;
				if (hasRepresentative(student.getId()))
				_allpersons += student.show(true);
				else if (hasStudent(student.getId()))
				_allpersons += student.show(false);

			}


			if(value instanceof Professor){
				Professor professor = (Professor) value;
				_allpersons += professor.show();
			}

			if(value instanceof Staff){
				Staff staff = (Staff) value;
				_allpersons += staff.show();
			}


		}
		return _allpersons;
	}



	/**
	* Perform the Command DoSearchPerson
	*
	* @param   name The String input by user to search
	* @return String
	*/

	//3.4
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



	/*=====================================
	=   Metodos de Portal DOCENTE         =
	=====================================*/
	//4.1
	public String createProject(){
		return "_school.createProject()";
	}
	//4.2
	public String closeProject(){
		return "_school.closeProject()";
	}
	//4.3
	public String showDisciplineStudent(){
		return "_school.showDisciplineStudent()";
	}
	//4.4
	public String showProjectSubmissions(){
		return "_school.showProjectSubmissions()";
	}
	//4.5
	public String showSurveyResults(){
		return "_school.showSurveyResults()";
	}

	/*=====================================
	=   Metodos de Portal ALUNO           =
	=====================================*/
	//5.1
	public String deliverProject() {
		return "_school.deliverProject()";
	}
	//5.2
	public String answerSurvey(){
		return "_school.answerSurvey()";
	}
	//5.3
	public String showSurveyResult(){
		return "_school.showSurveyResults()";
	}

	/*=====================================
	=   Metodos de Portal DELEGADO        =
	=====================================*/
	//6.1
	public String createSurvey() throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, DuplicateSurveyexcepcao{
		return "_school.createSurvey()";
	}
	//6.2
	public String cancelSurvey() throws NoSuchProjectexcepcao,SurveyFinishedexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, NonEmptySurveyexcepcao{
		return "_school.cancelSurvey()";
	}
	//6.3
	public String openSurvey() throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, OpeningSurveyexcepcao{
		return "_school.openSurvey()";
	}
	//6.4
	public String closeSurvey() throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, ClosingSurveyexcepcao{
		return "_school.closeSurvey()";
	}
	//6.5
	public String finishSurvey() throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, FinishingSurveyexcepcao{
		return "_school.finishSurvey()";
	}
	//6.6
	public String showDisciplineSurvey() throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao{
		return "_school.showDisciplineSurvey()";
	}

}
