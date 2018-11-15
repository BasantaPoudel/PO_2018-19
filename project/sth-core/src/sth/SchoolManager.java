package sth;
import java.io.IOException;

import sth.exceptions.BadEntryException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;

//FIXME [FIXING-BEGIN] import other classes if needed
import java.util.Collection;
import java.util.Collections;
import sth.exceptions.UnknownAgentException;

//FIXME [FIXING-END] import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager {

  //FIXME add object attributes if needed

  //FIXME implement constructors if needed
	private School _school = new School();
	private int _testid;

  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws ImportFileException {
  	try {
  		_school.importFile(datafile);
			// _school.printStudent();   //[Debug]

  	} catch (IOException | BadEntryException e) {
  		throw new ImportFileException(e);
  	}
  }



  /**
   * @param id
   * @throws NoSuchPersonIdException
   */
  public void login(int id) throws NoSuchPersonIdException {
    //FIXME [FIXING-BEGIN] implement method
		_testid = id;
		if (  !(_school.hasStudent(id)||_school.hasProfessor(id)||_school.hasRepresentative(id) || _school.hasStaff(id))	)
			throw new NoSuchPersonIdException(id);
   // System.out.println(_testid); [Debug]
		//FIXME [FIXING-END] implement method
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
	 public boolean hasAdministrative() {

   	// System.out.println(_school.hasAdministrative(10001));
   	return false;
   }

   /**
    * @return true when the currently logged in person is a professor
    */
   public boolean hasProfessor() {
		 return _school.hasProfessor(_testid);
   }

   /**
    * @return true when the currently logged in person is a student
    */
   public boolean hasStudent() {
		 return _school.hasStudent(_testid) ||  _school.hasRepresentative(_testid);
   }

   /**
    * @return true when the currently logged in person is a representative
    */
   public boolean hasRepresentative() {
		 return _school.hasRepresentative(_testid);
   }

//FIXME [FIXING-BEGIN] implement other methods (in general, one for each command in sth-app)
  // public String getPersons() {
  //     return _school.getPersons();
  // }

  // public Collection<Student> getStudentsCollection() {
  //    return Collections.unmodifiableCollection(_students.values());
  // }



  public String searchPerson(String name) throws UnknownAgentException{
    return _school.searchPerson(name);
  }

  // public String showAllPersons() throws UnknownAgentException{
  //  return _school.showAllPersons();
  // }


}
