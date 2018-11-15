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

import sth.exceptions.*;

import sth.core.Student;
import sth.core.Professor;
import sth.core.Discipline;
import sth.core.Person;
import sth.core.Project;
import sth.core.Staff;
import sth.core.Student;
import sth.core.Survey;

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

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
   			registerFromFields(fields);

                  // int id = Integer.parseInt(fields[1]); //incase we need 
   			while(isNextLineHashtag(reader)){
   				// line = reader.readLine();
           //[FIXME] System.out.println(line);
   			}

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
       	fields = line.split("\\#");
       	fields=fields[1].split("\\|");
       	// Discipline d = new Discipline(fields[0],fields[1]);
            // _professors.put(id,professor);

         reader.reset(); //checking was already done
         return true;
     }
 }
     reader.reset(); //checking was already done
     return false;

 }
   // ====================================================================================


 void registerFromFields(String[] fields) throws UnknownDataException,
 ClientExistsException,
 InvalidIdentifierException {

 	Pattern pattStudent = Pattern.compile("^(ALUNO)");
 	Pattern pattRepresentive  = Pattern.compile("^(DELEGADO)");
 	Pattern pattProfessor = Pattern.compile("^(DOCENTE)");
 	Pattern pattStaff = Pattern.compile("^(FUNCIONÁRIO)");


 	if (pattStudent.matcher(fields[0]).matches()) {
 		registerStudent(fields);
 	}
 	else if (pattRepresentive .matcher(fields[0]).matches()) {
 		registerRepresentive(fields);
 	}
 	else if (pattProfessor.matcher(fields[0]).matches()) {
 		registerProfessor(fields);
 	}
 	else if (pattStaff.matcher(fields[0]).matches()) {
 		registerStaff(fields);
 	}
 	else {
 		throw new UnknownDataException(fields[0]);
 	}
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
   void registerStudent(String[] fields) throws  UnknownDataException {

   	int id = Integer.parseInt(fields[1]);
   	int phoneNumber = Integer.parseInt(fields[2]);
   	String name = fields[3];

   	if (fields[0].equals("ALUNO")) {
       //...|id|tel|name
   		Student s= new Student(name,phoneNumber,id);
   		addStudent(id, s);
   	}
   }

   void registerRepresentive(String[] fields) throws  UnknownDataException {
   	int id = Integer.parseInt(fields[1]);
   	int phoneNumber = Integer.parseInt(fields[2]);
   	String name = fields[3];

   	if (fields[0].equals("DELEGADO")) {
       //...|id|tel|name
   		Student repr= new Student(name,phoneNumber,id);
   		addRepresentive(id, repr);
   	}
   }

   void registerProfessor(String[] fields) throws  UnknownDataException {
   	int id = Integer.parseInt(fields[1]);
   	int phoneNumber = Integer.parseInt(fields[2]);
   	String name = fields[3];

   	if (fields[0].equals("DOCENTE")) {
       //...|id|tel|name
   		Professor t= new Professor(name,phoneNumber,id);
   		addProfessor(id, t);
   	}
   }

   void registerStaff(String[] fields) throws  UnknownDataException {

   	int id = Integer.parseInt(fields[1]);
   	int phoneNumber = Integer.parseInt(fields[2]);
   	String name = fields[3];

   	if (fields[0].equals("FUNCIONÁRIO")) {
       //...|id|tel|name
   		Staff s= new Staff(name,phoneNumber,id);
   		addStaff(id, s);
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
}
