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



//FIXME [FIXING-END] import other classes if needed

/**
* School implementation.
*/
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;


	//FIXME [FIXING-BEGIN] define object fields (attributes and, possibly, associations)
	private int _auxId;

	// maps for people and theirs ids
	private TreeMap<Integer, Student> _students = new TreeMap<Integer, Student>();
	private TreeMap<Integer, Student> _representatives = new TreeMap<Integer, Student>();
	private TreeMap<Integer, Professor> _professors = new TreeMap<Integer, Professor>();
	private TreeMap<Integer, Staff> _staffs = new TreeMap<Integer, Staff>();
	//all people
	private TreeMap<Integer, Person> _persons= new TreeMap<Integer, Person>();


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
	* @param id int
	* @return true if the Map storing students finds a student with the given id in the Map.
	*/

	public boolean hasStudent(int id){
		return _students.containsKey(id);
	}

	/**
	*
	*
	* @param id int
	* @return true if the Map storing Professors finds a professor with the given id in the Map.
	*/

	public boolean hasProfessor(int id){
		return _professors.containsKey(id);
	}

	/**
	*
	*
	* @param id int
	* @return true if the Map storing Staffs finds a staff with the given id in the Map.
	*/

	public boolean hasStaff(int id){
		return _staffs.containsKey(id);
	}

	/**
	*
	*
	* @param id int
	* @return true if the Map storing Representatives finds a representative with the given id in the Map.
	*/

	public boolean hasRepresentative(int id){
		return _representatives.containsKey(id);
	}
	/**
	*
	*
	* @param id int
	* @return true if the Map storing Staffs finds a staff with the given id in the Map.
	*/

	public boolean hasStaffs(int id){
		return _staffs.containsKey(id);
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
	*Performs the Command DoShowPerson
	*
	* @param   id int
	* @return String
	*/

	public String showPerson(int id){
		Student _pStudent = _students.get(id);
		Professor _pProf = _professors.get(id);
		Student _pRepr = _representatives.get(id);
		Staff _pStaff = _staffs.get(id);
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
	* Perform the Command DoSearchPerson
	*
	* @param   name The String input by user to search
	* @return String
	*/
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

	/**
	*Perform the Command DoChangePhoneNumber
	*
	* @param   id id iof the person to change the number
	* @param   newTelPhone new telephone number to set in the Map.
	* @return String
 	*/
	public String setNewPhoneNum(int id,int newTelPhone){
		if (_students.get(id)!=null){
			Student student = _students.get(id);
			Person globalStudent = _persons.get(id);
			student.setPhoneNumber(newTelPhone);
			globalStudent.setPhoneNumber(newTelPhone);
		}

		else if (_representatives.get(id)!=null) {
			Student representative = _representatives.get(id);
			Person globalRepresentative = _persons.get(id);
			representative.setPhoneNumber(newTelPhone);
			globalRepresentative.setPhoneNumber(newTelPhone);

		}

		else if (_professors.get(id)!=null) {
			Professor professor = _professors.get(id);
			Person globalProfessor = _persons.get(id);
			professor.setPhoneNumber(newTelPhone);
			globalProfessor.setPhoneNumber(newTelPhone);
		}

		else if (_staffs.get(id)!=null) {
			Staff staff = _staffs.get(id);
			Person globalStaff = _persons.get(id);
			staff.setPhoneNumber(newTelPhone);
			globalStaff.setPhoneNumber(newTelPhone);
		}

		return showPerson(id);
		// printStudent();
	}

}
