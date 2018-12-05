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
import sth.exceptions.newexceptions.DuplicateProjectCoreException;



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

	//New Map for storing instances of disciplines
	private Map<String, Discipline> _disciplines= new TreeMap<String, Discipline>();

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


		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();

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
				Discipline discipline = new Discipline(c,fields3[1]);
				// discipline adds the id to 'it-self'
				disciplines.add(discipline);

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
			Student student = new Student(name,phoneNumber,id,false);
			for(Discipline discipline : disciplines){
				student.putDiscipline(discipline);
				discipline.putStudent(student);
			}
			addStudent(id, student);
			// Se calhar falta aqui adicionar alunos nas disciplinas.

		}
		else if (pattDELEGADO.matcher(tipoElemento).matches()){
			Student representive = new Student(name,phoneNumber,id,true);
			for(Discipline discipline : disciplines){
				representive.putDiscipline(discipline);
			}
			addRepresentive(id, representive);

		}
		else if (pattDOCENTE.matcher(tipoElemento).matches()){
			Professor professor = new Professor(name,phoneNumber,id);
			for(Discipline discipline : disciplines){
				professor.putDiscipline(discipline);
			}
			addProfessor(id, professor);

		}
		else if (pattFUNCIONÁRIO.matcher(tipoElemento).matches()){
			Staff staff = new Staff(name,phoneNumber,id);
			for(Discipline discipline : disciplines){
				staff.putDiscipline(discipline);
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
	public void createProject(int loginID, String disciplineName,String projectName)throws NoSuchDisciplineCoreException, DuplicateProjectCoreException{
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
				throw new DuplicateProjectCoreException(disciplineName,projectName);
			}
		}
		else {
			throw new NoSuchDisciplineCoreException(disciplineName);
		}
	}
	//4.2
	public void closeProject(int loginID,String disciplineName,String projectName)throws NoSuchDisciplineCoreException,NoSuchProjectCoreException{
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
				throw new NoSuchProjectCoreException(disciplineName,projectName);
			}


		}
		else{
			throw new NoSuchDisciplineCoreException(disciplineName);
		}
	}

	//4.4
	public String showProjectSubmissions(int loginID,String disciplineName,String projectName)throws NoSuchDisciplineCoreException,NoSuchProjectCoreException{
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
				return res;
			}
			else{
				throw new NoSuchProjectCoreException(disciplineName,projectName);
			}


		}
		else{
			throw new NoSuchDisciplineCoreException(disciplineName);
		}

		// Programação com Objectos - Gatos Simples
		// * 0234 - Gato.java
		// * 6789 - Cat.java
		// * 7912 - Tigre.jav
	}
	//4.3
	public String showDisciplineStudents(int loginID,String disciplineName)throws NoSuchDisciplineCoreException{
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
			throw new NoSuchDisciplineCoreException(disciplineName);
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
