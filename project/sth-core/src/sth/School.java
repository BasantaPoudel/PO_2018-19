package sth;

//FIXME [FIXING-BEGIN] import other classes if needed
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

//[FIXING-END]
import sth.exceptions.BadEntryException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements Serializable {

//   /** Serial number for serialization. */
//   private static final long serialVersionUID = 201810051538L;

//   //FIXME define object fields (attributes and, possibly, associations)

//   //FIXME implement constructors if needed

//   /**
//    * @param filename
//    * @throws BadEntryException
//    * @throws IOException
//    */
//   void importFile(String filename) throws IOException, BadEntryException {
//     //FIXME [FIXING-BEGIN] implement text file reader
//    try {
//      String s = new String();
//      BufferedReader in = new BufferedReader(new FileReader(filename));
//      PrintWriter   out = new PrintWriter(new BufferedWriter(new FileWriter("Escritor1.out")));
//      int lineCount = 1;
//      while((s = in.readLine()) != null ){
//                      String[] fields = s.split("\\|");
//                    for (String i:fields){
//                      System.out.println(i);
//                    }
//           out.printf("%3d: %s\n", lineCount++, s);
//           }
//           out.close();
//      in.close();
//    }
//    catch(IOException e) { System.err.println("Erro Ler o Ficheiro"); }
//    //catch(BadEntryException e) { System.err.println("Erro Ler o Ficheiro"); }
//    //[FIXING-END]
//   }


// ____________________________________________________________________________________________________
// simple factory to populate the school system with people

	// void registerFromFields(String[] fields) throws UnknownDataException{
 //                // Regular expression pattern to match an person.
	// 	Pattern patPerson = Pattern.compile("^(FUNCIONÁRIO|DELEGADO|ALUNO|DOCENTE)");
 //                // Regular expression pattern to match a message.
	// 	Pattern patMessage = Pattern.compile("^(MESSAGE)");
 //                // Regular expression pattern to match a publication.
	// 	Pattern parPublication = Pattern.compile("^(IMAGE|NOTE|URI)");
 //                // Regular expression pattern to match a publication.
	// 	Pattern parConnection = Pattern.compile("^(CONNECTION)");

	// 	if (patPerson.matcher(fields[0]).matches()) {
	// 		registerPerson(fields);
	// 	} else if (patMessage.matcher(fields[0]).matches()) {
	// 		registerMessage(fields);
	// 	} else if (parPublication.matcher(fields[0]).matches()) {
	// 		registerPublication(fields);
	// 	} else if (parConnection.matcher(fields[0]).matches()) {
	// 		registerConnection(fields);
	// 	} else {
	// 		throw new UnknownDataException(fields[0]);
	// 	}
	// }


	// void registerPerson(String... fields) throws ClientExistsException, UnknownDataException {
 //                int id = (fields[1] != null) ? Integer.parseInt(fields[1]) : getUUID();
 //                if (fields[0].equals("PERSON")) {
 //                        Person person = new Person(id, fields[2], fields[3], fields[4]);
 //                        .addPerson(id, person);
 //                        setLastUUID(id);
 //                        changed();
 //                } else if (fields[0].equals("ORGANIZATION")) {
 //                        Organization organization = new Organization(id, fields[2], fields[3], fields[4]);
 //                        addPerson(id, organization);
 //                        setLastUUID(id);
 //                        changed();
 //                } else {
 //                        throw new UnknownDataException(fields[0]);
 //                }
 //        }



  //FIXME  implement other methods
//   // FIXING

// //auxiliary methods for the creation of the several elements of the school

//   /**
//    * @param id
//    * @param phoneNumber
//    * @param name
//    * @throws CantAddStudentException
//    */
//   public void addStudent(int id,int phoneNumber,String name) throws CantAddStudentException {
//     //NEWFIXME implement method
//    // FIXING-BEGIN

//    // >100000
//   	id_size=(int)(Math.log10(id)+1);
//   	// conditions
//   	if (id>100000 && size<=6){
//   		students.add(new Student(id,phoneNumber,name));
//   	}
//   	else {
//   		throw CantAddStudentException;
//   	}

//   }

//     /**
//    * @param id
//    * @param phoneNumber
//    * @param name
//    * @throws CantAddTeacherException
//    */
//   public void addTeacher(int id,int phoneNumber,String name) throws CantAddTeacherException {
//     //NEWFIXME implement method
//    // FIXING-BEGIN

//    // >100000
//   	id_size=(int)(Math.log10(id)+1);
//   	// conditions
//   	if (id>100000 && size<=6){
//   		teachers.add(new Teacher(id,phoneNumber,name));
//   	}
//   	else {
//   		throw CantAddTeacherException;
//   	}

//   }

//     /**
//    * @param id
//    * @param phoneNumber
//    * @param name
//    * @throws CantAddStaffException
//    */
//   public void addStaff(int id,int phoneNumber,String name) throws CantAddStaffException {
//     //NEWFIXME implement method
//    // FIXING-BEGIN

//    // >100000
//   	id_size=(int)(Math.log10(id)+1);
//   	// conditions
//   	if (id>100000 && size<=6){
//   		staffs.add(new Staff(id,phoneNumber,name));
//   	}
//   	else {
//   		throw CantAddStaffException;
//   	}

//   }

//       /**
//    * @param id
//    * @param phoneNumber
//    * @param name
//    * @throws CantAddRepresentativeException
//    */
//   public void addRepresentative(int id,int phoneNumber,String name) throws CantAddRepresentativeException {
//     //NEWFIXME implement method
//    // FIXING-BEGIN

//    // >100000
//   	id_size=(int)(Math.log10(id)+1);
//   	// conditions
//   	if (id>100000 && size<=6){
//   		Representatives.add(new Representative(id,phoneNumber,name));
//   	}
//   	else {
//   		throw CantAddRepresentativeException;
//   	}

//   }



}