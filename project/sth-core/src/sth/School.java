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
import sth.core.ProjectSubmission;
import sth.core.Staff;
import sth.core.Student;
import sth.core.Survey;
import sth.core.Course;

import sth.exceptions.newexceptions.NonEmptySurveyNewException;
import sth.exceptions.newexceptions.OpeningSurveyNewException;
import sth.exceptions.newexceptions.ClosingSurveyNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;
import sth.exceptions.newexceptions.NoSurveyNewException;
import sth.exceptions.newexceptions.FinishingSurveyNewException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.DuplicateSurveyNewException;
import sth.exceptions.newexceptions.SurveyFinishedNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;
import sth.exceptions.newexceptions.DuplicateProjectNewException;



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

	// Maps for storing instances
	private Map<String, Discipline> _disciplines= new TreeMap<String, Discipline>();
	private Map<String, Course> _courses= new TreeMap<String, Course>();
	private Map<String,Project> _projects = new TreeMap<String,Project>();

	// <discipline name, Map of courses>
	// private Map<String,ArrayList<Course> > _disciplinesCourses = new TreeMap<String,ArrayList<Course> >();

	//[FIXING-END]
	//FIXME implement constructors if needed

	/*========================================
	=     Methods for Import and Parsing
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

		//ArrayList to temporarily store disciplines and courses
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		ArrayList<String> _courseTempNames = new ArrayList<String>();

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
				// if(_courseTempNames.contains(fields3[0]))
				// System.out.println("Cousename in isNextLineHashtag: "+fields3[0]);
				//make the necessary objects for adding the discipline to student
				_courseTempNames.add(fields3[0]);
				if(_courses.containsKey(fields3[0])==false){  //fields3[0]: CourseName
					Course c = new Course(fields3[0]);
					if(c.hasDiscipline(fields3[1])==false){
					Discipline discipline = new Discipline(c,fields3[1]);
						c.putDiscipline(discipline);
						_courses.put(fields3[0],c);
						disciplines.add(discipline);
						// System.out.println("CourseName:" +fields3[0] +" "+c+ " DisciplineName: "+fields3[1] +" "+ discipline);
					}
					else{
						Discipline discipline = c.getDiscipline(fields3[1]);
						c.putDiscipline(discipline);
						_courses.put(fields3[0],c);
						disciplines.add(discipline);
						// System.out.println("CourseName:" +fields3[0] +" "+c+ " DisciplineName: "+fields3[1] +" "+ discipline);
					}
					// System.out.println("Printing in isNextLineHashtag");
				}

				else{
					Course c = _courses.get(fields3[0]);

					if(c.hasDiscipline(fields3[1])==false){
					Discipline discipline = new Discipline(c,fields3[1]);
						c.putDiscipline(discipline);
						_courses.put(fields3[0],c);
						disciplines.add(discipline);
						// System.out.println("CourseName:" +fields3[0] +" "+c+ " DisciplineName: "+fields3[1] +" "+ discipline);
					}
					else{
						Discipline discipline = c.getDiscipline(fields3[1]);
						c.putDiscipline(discipline);
						_courses.put(fields3[0],c);
						disciplines.add(discipline);
						// System.out.println("CourseName:" +fields3[0] +" "+c+ " DisciplineName: "+fields3[1] +" "+ discipline);
					}
				}
			}
		}catch(UnknownDataException e){
			// FIX
		}   		catch(IOException e){
			e.printStackTrace();// FIX
		}  		catch(ClientExistsException e){
			e.printStackTrace();// FIX
		}		catch(InvalidIdentifierException e){
			e.printStackTrace();// FIX
		}
		// _______________________________________________________________________


		if (pattALUNO.matcher(tipoElemento).matches()){
			Student student = new Student(name,phoneNumber,id,false);
			int i=0;
			for(Discipline discipline : disciplines){
				// System.out.println(discipline.getName());
				Course c = _courses.get(_courseTempNames.get(i));
				// System.out.println(c.getName());
				// System.out.println(_courseTempNames.get(i));
				Discipline d = c.getDiscipline(discipline.getName());
				// System.out.println(d.getName());
				student.putDiscipline(d);
				// System.out.println("Aluno     "+name+" Curso: "+_courseTempNames.get(i) + " Discipline: " + discipline.getName()+ " "+d);

				d.putStudent(student);
				// student.putDiscipline(discipline);
				// discipline.putStudent(student);
				i=i+1;
			}
			addStudent(id, student);
			// Se calhar falta aqui adicionar alunos nas disciplinas.

		}
		else if (pattDELEGADO.matcher(tipoElemento).matches()){
			Student representive = new Student(name,phoneNumber,id,true);
			int i =0;
			for(Discipline discipline : disciplines){
				// System.out.println(discipline.getName());
				Course c = _courses.get(_courseTempNames.get(i));
				Discipline d = c.getDiscipline(discipline.getName());
				//
				representive.putDiscipline(d);
				d.putStudent(representive);
				// System.out.println("Delegado     "+name+" Curso: "+_courseTempNames.get(i) + " Discipline: " + discipline.getName()+ " "+d);

				 // representive.putDiscipline(discipline);
				i=i+1;
			}
			addRepresentive(id, representive);

		}
		else if (pattDOCENTE.matcher(tipoElemento).matches()){
			Professor professor = new Professor(name,phoneNumber,id);
			// System.out.println(_courseTempNames.size());
			int i =0;
			for(Discipline discipline : disciplines){
				// System.out.println(discipline.getName());
				Course c = _courses.get(_courseTempNames.get(i));
				Discipline d = c.getDiscipline(discipline.getName());
				professor.putCourse(_courseTempNames.get(i),c);
				i=i+1;
				// professor.putDiscipline(discipline);
				professor.putDiscipline(d);

			}
			addProfessor(id, professor);

		}
		else if (pattFUNCIONÁRIO.matcher(tipoElemento).matches()){
			Staff staff = new Staff(name,phoneNumber,id);
			int i=0;
			for(Discipline discipline : disciplines){
				Course c = _courses.get(_courseTempNames.get(i));
				Discipline d = c.getDiscipline(discipline.getName());
				staff.putDiscipline(discipline);
				// System.out.println("Funcionario     "+name+" Curso: "+_courseTempNames.get(i) + " Discipline: " + discipline.getName()+ " "+d);

				i=i+1;
			}
			addStaff(id, staff);

		}
		else{
			// FIX
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
		_persons.put(id,student);
	}

	/**
	* Adds representive to proper array
	*
	* @param id int
	* @param student Student
	*/
	public void addRepresentive (int id, Student student ){
		_representatives.put(id,student);
		_persons.put(id,student);
	}

	/**
	* Adds professor to proper array
	*
	* @param id int
	* @param professor Professor
	*/

	public void addProfessor(int id, Professor professor){
		_professors.put(id,professor);
		_persons.put(id,professor);
	}

	/**
	* Adds staff to proper array
	*
	* @param id int
	* @param staff Staff
	*/

	public void addStaff(int id, Staff staff){
		_staffs.put(id,staff);
		_persons.put(id,staff);
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
			return _pStudent.show();
		}
		else if(_pRepr!=null){
			return _pRepr.show();
		}
		else if(_pProf!=null){
			return _pProf.showWithDisciplines();
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
		TreeMap<Integer,Person> allPeople = new TreeMap<Integer,Person>();

		for (Integer personId : _persons.keySet()) {
			if(hasStudent(personId) ){
				allPeople.put(personId,_students.get(personId)) ;
			}
			if(hasRepresentative(personId)){
				allPeople.put(personId,_representatives.get(personId)) ;
			}
			if(hasProfessor(personId) ){
				allPeople.put(personId,_professors.get(personId)) ;
			}
			if(hasStaff(personId) ){
				allPeople.put(personId,_staffs.get(personId)) ;
			}
		}
		String res="";
		for (Integer id : allPeople.keySet() ){
			Person person = allPeople.get(id);
			if (_staffs.containsKey(person.getId())){
				res+=person.show();
			}
			else {
				res+=person.showWithDisciplines();
			}
		}

		return res;

	}


	/**
	* Perform the Command DoSearchPerson
	*
	* @param   name The String input by user to search
	* @return String
	*/

	//3.4
	public String searchPerson(String searchName) throws UnknownAgentException{
		TreeMap<String,Person> allPeople = new TreeMap<String,Person>();


		for (Integer id : _professors.keySet() ){

			Professor professor = _professors.get(id);
			String name =professor.getName();

			if(name.contains(searchName)){
				allPeople.put(professor.getName(),professor);
			}
		}
		for (Integer id : _students.keySet() ){

			Student student = _students.get(id);
			String name =student.getName();

			if(name.contains(searchName)){
				allPeople.put(student.getName(),student);
			}
		}
		for (Integer id : _representatives.keySet() ){

			Student student = _representatives.get(id);
			String name =student.getName();

			if(name.contains(searchName)){
				allPeople.put(student.getName(),student);
			}
		}
		for (Integer id : _staffs.keySet() ){

			Staff staff = _staffs.get(id);
			String name =staff.getName();

			if(name.contains(searchName)){
				allPeople.put(staff.getName(),staff);
			}
		}

		// finally we make the whole string to be used

		String res="";
		for (String name : allPeople.keySet() ){
			Person person = allPeople.get(name);
			if (_staffs.containsKey(person.getId())){
				res+=person.show();
			}
			else {
				res+=person.showWithDisciplines();
			}
		}

		return res;
	}



	/*=====================================
	=   Metodos de Portal DOCENTE         =
	=====================================*/
	//4.1
	public void createProject(int loginID, String disciplineName,String projectName)throws NoSuchDisciplineNewException, DuplicateProjectNewException{
		Professor prof = _professors.get(loginID);
		if (prof.hasDiscipline(disciplineName)){
			// courseName
			String courseName=prof.getDisciplineCourseName(disciplineName);
			// discipline
			Discipline discipline = prof.getDiscipline(courseName,disciplineName);
			// project
			if (discipline.hasProject(projectName)==false){
				Project proj=new Project(projectName);

				discipline.addProject(proj);
			}
			else{
				throw new DuplicateProjectNewException(disciplineName,projectName);
			}
		}
		else {
			throw new NoSuchDisciplineNewException(disciplineName);
		}
	}
	//4.2
	public void closeProject(int loginID,String disciplineName,String projectName)throws NoSuchDisciplineNewException,NoSuchProjectNewException{
			Professor prof = _professors.get(loginID);
		if (prof.hasDiscipline(disciplineName)){
			// courseName
			String courseName=prof.getDisciplineCourseName(disciplineName);
			// discipline
			Discipline discipline = prof.getDiscipline(courseName,disciplineName);
			// project
			Project proj=discipline.getProject(projectName);

			if (discipline.hasProject(projectName)==true){
				proj.close();
			}
			else{
				throw new NoSuchProjectNewException(disciplineName,projectName);
			}


		}
		else{
			throw new NoSuchDisciplineNewException(disciplineName);
		}
	}
	//4.3
	public String showDisciplineStudents(int loginID,String disciplineName)throws NoSuchDisciplineNewException{
		// DELEGADO|100008|123456789|Joaquim Maria
		// * Informática - Algoritmos e Estruturas de Dados
		// * Informática - Análise e Síntese de Algoritmos
		// * Informática - Fundamentos
		// * Informática - Programação com Objectos
		// DELEGADO|100009|123456789|João Maria
		// * Informática - Algoritmos e Estruturas de Dados
		// * Informática - Análise e Síntese de Algoritmos
		// * Informática - Fundamentos
		// * Informática - Programação com Objectos
		// DELEGADO|100011|123456789|João Manuel
		// * Informática - Algoritmos e Estruturas de Dados
		// * Informática - Análise e Síntese de Algoritmos
		// * Informática - Fundamentos
		// * Informática - Programação com Objectos
		Professor prof = _professors.get(loginID);
		if (prof.hasDiscipline(disciplineName)){
			// System.out.println("Has Discipline"); //[debug]
			String res = ""; //+" - "+projectName;

			// // courseName
			// String courseName=prof.getDisciplineCourseName(disciplineName);

			// // discipline
			// Discipline discipline = prof.getDiscipline(courseName,disciplineName);
			res += prof.showDisciplineStudents(disciplineName);
			// project
			// Project proj=discipline.getProject(projectName);
			// res+=proj.getSubmissions();

			return res;
		}
		else{
			throw new NoSuchDisciplineNewException(disciplineName);
		}
	}


	//4.4
	public String showProjectSubmissions(int loginID,String disciplineName,String projectName)throws NoSuchDisciplineNewException,NoSuchProjectNewException{
		Professor prof = _professors.get(loginID);
		if (prof.hasDiscipline(disciplineName)){

			String res = disciplineName+" - "+projectName;
			// courseName
			String courseName=prof.getDisciplineCourseName(disciplineName);
			// discipline
			Discipline discipline = prof.getDiscipline(courseName,disciplineName);
			// project
			Project proj=discipline.getProject(projectName);

			if (discipline.hasProject(projectName)==true){
				// proj.close();
				// return res;
					res += proj.showSubmissions();
					// System.out.println(res);
					return res;
			}
		 	else{
				throw new NoSuchProjectNewException(disciplineName,projectName);
			}
		}
		else{
			throw new NoSuchDisciplineNewException(disciplineName);
		}

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
	public void deliverProject(int loginID, String disciplineName, String projectName,String _description) throws NoSuchDisciplineNewException,NoSuchProjectNewException{
		// return "_school.deliverProject()";
		// Student loggedStudent = _students.get(loginID);
		// Student loggedRepresentative = _representatives.get(loginID);
		Student loggedStudent = null;
		if(hasStudent(loginID)){
			 loggedStudent = _students.get(loginID);
		}
		else if(hasRepresentative(loginID)){
			loggedStudent = _representatives.get(loginID);
		}
		// System.out.println("asassd" + loggedRepresentative);
		if (loggedStudent.hasDiscipline(disciplineName)){
		// courseName
		// String courseName=prof.getDisciplineCourseName(disciplineName);
		// discipline

		Discipline discipline = loggedStudent.getDiscipline(disciplineName);
		// project
		ProjectSubmission projSub = new ProjectSubmission(_description);
		// System.out.println(projSub);
		if (discipline.hasProject(projectName)){
			Project proj=discipline.getProject(projectName);
			if(proj.getState()==false){
				proj.submitProject(loginID,projSub);
			}
			else{
				// System.out.println("State Check");
				throw new NoSuchProjectNewException(disciplineName,projectName);
			}
			// System.out.println("Delivered Project: "+ proj.getProjectSubmitted(loginID));
			// System.out.println("Has Submission: "+ proj.hasSubmission(loginID));
		}
		else{
			// System.out.println("Existence Check");
			throw new NoSuchProjectNewException(disciplineName,projectName);
		}


	}
	else{
		throw new NoSuchDisciplineNewException(disciplineName);
	}
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
	public String createSurvey() throws NoSuchProjectNewException, NoSuchDisciplineNewException, DuplicateSurveyNewException{
		return "_school.createSurvey()";
	}
	//6.2
	public String cancelSurvey() throws NoSuchProjectNewException,SurveyFinishedNewException, NoSuchDisciplineNewException, NoSurveyNewException, NonEmptySurveyNewException{
		return "_school.cancelSurvey()";
	}
	//6.3
	public String openSurvey() throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, OpeningSurveyNewException{
		return "_school.openSurvey()";
	}
	//6.4
	public String closeSurvey() throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, ClosingSurveyNewException{
		return "_school.closeSurvey()";
	}
	//6.5
	public String finishSurvey() throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, FinishingSurveyNewException{
		return "_school.finishSurvey()";
	}
	//6.6
	public String showDisciplineSurvey() throws NoSuchProjectNewException, NoSuchDisciplineNewException{
		return "_school.showDisciplineSurvey()";
	}

}
