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
import java.util.ArrayList;

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


import sth.exceptions.newexceptions.NonEmptySurveyCoreException;
import sth.exceptions.newexceptions.OpeningSurveyCoreException;
import sth.exceptions.newexceptions.ClosingSurveyCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;
import sth.exceptions.newexceptions.NoSurveyCoreException;
import sth.exceptions.newexceptions.FinishingSurveyCoreException;
import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.DuplicateSurveyCoreException;
import sth.exceptions.newexceptions.SurveyFinishedCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;



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

	private Map<String,Project> _projects = new TreeMap<String,Project>();

	// <discipline name, Map of courses>
	private Map<String,ArrayList<Course> > _disciplinesCourses = new TreeMap<String,ArrayList<Course> >();





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
			String[] fields = line.split("\\|");
			try {
				register(fields,reader);
			} catch (UnknownDataException e) {
				System.err.printf("WARNING: unknown data");
				e.printStackTrace();
			}
		}
		reader.close();
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



	/**
	* Registers
	*
	* @param    fields
	* @param    reader
	* @throws  UnknownDataException
	*/
	void register(String[] fields, BufferedReader reader) throws  UnknownDataException {

		// vars for creation of the new school element
		int id = Integer.parseInt(fields[1]);
		int phoneNumber = Integer.parseInt(fields[2]);
		String name = fields[3];

		ArrayList<Discipline> discs = new ArrayList<Discipline>();

		String tipoElemento="";
		Pattern pattALUNO = Pattern.compile("ALUNO");
		Pattern pattDELEGADO = Pattern.compile("DELEGADO");
		Pattern pattDOCENTE = Pattern.compile("DOCENTE");
		Pattern pattFUNCIONÁRIO = Pattern.compile("FUNCIONÁRIO");

		if (! (	pattALUNO.matcher(fields[0]).matches()||	pattDELEGADO.matcher(fields[0]).matches()||	pattDOCENTE.matcher(fields[0]).matches()||	pattFUNCIONÁRIO.matcher(fields[0]).matches() ) ){
			throw new UnknownDataException();
		}

		if (pattALUNO.matcher(fields[0]).matches()){
			tipoElemento="ALUNO";
		}

		if (pattDELEGADO.matcher(fields[0]).matches()){
			tipoElemento="DELEGADO";
		}

		if (pattDOCENTE.matcher(fields[0]).matches()){
			tipoElemento="DOCENTE";
		}

		if (pattFUNCIONÁRIO.matcher(fields[0]).matches()){
			tipoElemento="FUNCIONÁRIO";
		}

		// _______________________________________________________________________
		// cycle that is used when the following line has an hashtag
		try{
			while(isNextLineHashtag(reader)){
				String line=reader.readLine();
				// for each # line we will:

				// cut out the # part
				String[] fields2 = line.split("\\#");

				//gets last 2 fields for discipline
				String[] fields3=fields2[1].split("\\|");

				//make the necessary objects for adding the discipline to student

				Course c = new Course(fields3[0]);
				Discipline disc = new Discipline(c,fields3[1]);
				// discipline adds the id to 'it-self'
				discs.add(disc);

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
		// _______________________________________________________________________



		if (pattALUNO.matcher(tipoElemento).matches()){
			Student student = new Student(name,phoneNumber,id);
			for(Discipline disc : discs){
				student.putDiscipline(disc);
			}
			addStudent(id, student);
			addPerson(id, student);

		}
		else if (pattDELEGADO.matcher(tipoElemento).matches()){
			Student representive = new Student(name,phoneNumber,id);
			for(Discipline disc : discs){
				representive.putDiscipline(disc);
			}
			addRepresentive(id, representive);
			addPerson(id, representive);

		}
		else if (pattDOCENTE.matcher(tipoElemento).matches()){
			Professor professor = new Professor(name,phoneNumber,id);
			for(Discipline disc : discs){
				professor.putDiscipline(disc);
			}
			addProfessor(id, professor);
			addPerson(id, professor);

		}
		else if (pattFUNCIONÁRIO.matcher(tipoElemento).matches()){
			Staff staff = new Staff(name,phoneNumber,id);
			for(Discipline disc : discs){
				staff.putDiscipline(disc);
			}
			addStaff(id, staff);
			addPerson(id, staff);

		}
		else{
			System.out.println("??");
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
	public void createProject(int loginID, String disciplineName,String projectName)throws NoSuchDisciplineCoreException{
		Professor prof = _professors.get(loginID);
		if (prof.hasDiscipline(disciplineName)){
			// courseName
			String courseName=prof.getDisciplineCourseName(disciplineName);
			// discipline
			Discipline disc = prof.getDiscipline(courseName,disciplineName);

			// project
			Project proj=new Project(projectName);
			disc.addProject(proj);
		}
		else{
			throw new NoSuchDisciplineCoreException(disciplineName);
		}
	}
	//4.2
	public void closeProject(int loginID,String disciplineName,String projectName)throws NoSuchDisciplineCoreException{
				Professor prof = _professors.get(loginID);
				if (prof.hasDiscipline(disciplineName)){
			// courseName
			String courseName=prof.getDisciplineCourseName(disciplineName);
			// discipline
			Discipline disc = prof.getDiscipline(courseName,disciplineName);

			// project
			Project proj=disc.getProject(projectName);
			proj.close();

		}
		else{
			throw new NoSuchDisciplineCoreException(disciplineName);
		}
	}

	//4.4
	public String showProjectSubmissions(int loginID,String disciplineName,String projectName){
		String res = "";
		
		return res;
		// Programação com Objectos - Gatos Simples
		// * 0234 - Gato.java
		// * 6789 - Cat.java
		// * 7912 - Tigre.jav
	}
	//4.3
	public String showDisciplineStudents(int loginID,String disciplineName,String projectName){
		return "";
	}


	// Survey
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
	public String createSurvey() throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, DuplicateSurveyCoreException{
		return "_school.createSurvey()";
	}
	//6.2
	public String cancelSurvey() throws NoSuchProjectCoreException,SurveyFinishedCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, NonEmptySurveyCoreException{
		return "_school.cancelSurvey()";
	}
	//6.3
	public String openSurvey() throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, OpeningSurveyCoreException{
		return "_school.openSurvey()";
	}
	//6.4
	public String closeSurvey() throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, ClosingSurveyCoreException{
		return "_school.closeSurvey()";
	}
	//6.5
	public String finishSurvey() throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, FinishingSurveyCoreException{
		return "_school.finishSurvey()";
	}
	//6.6
	public String showDisciplineSurvey() throws NoSuchProjectCoreException, NoSuchDisciplineCoreException{
		return "_school.showDisciplineSurvey()";
	}

}
