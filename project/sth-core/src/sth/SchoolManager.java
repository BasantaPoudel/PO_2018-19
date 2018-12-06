package sth;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

import sth.exceptions.BadEntryException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;

//FIXME [FIXING-BEGIN] import other classes if needed
import java.util.Collection;
import java.util.Collections;
import sth.exceptions.UnknownAgentException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import sth.exceptions.newexceptions.NoSurveyNewException;
import sth.exceptions.newexceptions.ClosingSurveyNewException;
import sth.exceptions.newexceptions.NoSuchProjectNewException;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;
import sth.exceptions.newexceptions.FinishingSurveyNewException;
import sth.exceptions.newexceptions.OpeningSurveyNewException;
import sth.exceptions.newexceptions.DuplicateSurveyNewException;
import sth.exceptions.newexceptions.NonEmptySurveyNewException;
import sth.exceptions.newexceptions.SurveyFinishedNewException;
import sth.exceptions.newexceptions.DuplicateProjectNewException;

//FIXME [FIXING-END] import other classes if needed

/**
* The façade class.
*/
public class SchoolManager {

	//FIXME add object attributes if needed

	//FIXME implement constructors if needed
	private School _school = new School();
	private School _schoolCurrent;
	private int _loginID;
	private boolean _ischanged = false;
	private boolean _initial = true;

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
		_loginID = id;
		if (  !(_school.hasStudent(id)||_school.hasProfessor(id)||_school.hasRepresentative(id) || _school.hasStaff(id))	)
		throw new NoSuchPersonIdException(id);
		// System.out.println(_loginID); [Debug]
		// _school.printStudent();
		//FIXME [FIXING-END] implement method
	}

	/**
	* @return true when the currently logged in person is an administrative
	*/
	public boolean hasAdministrative() {

		// System.out.println(_school.hasAdministrative(10001));
		return false;
	}

	public void hasUser(int id) throws NoSuchPersonIdException{
		if (  !(_school.hasStudent(id)||_school.hasProfessor(id)||_school.hasRepresentative(id) || _school.hasStaff(id))	)
		throw new NoSuchPersonIdException(id);
	}

	/**
	* @return true when the currently logged in person is a professor
	*/
	public boolean hasProfessor() {
		return _school.hasProfessor(_loginID);
	}

	/**
	* @return true when the currently logged in person is a student
	*/
	public boolean hasStudent() {
		return _school.hasStudent(_loginID) ||  _school.hasRepresentative(_loginID);
	}

	/**
	* @return true when the currently logged in person is a representative
	*/
	public boolean hasRepresentative() {
		return _school.hasRepresentative(_loginID);
	}

	//FIXME [FIXING-BEGIN] implement other methods (in general, one for each command in sth-app)
	// public String getPersons() {
	//     return _school.getPersons();
	// }



	public String setNewPhoneNum(int newTelPhone){
		_ischanged=true;
		return _school.setNewPhoneNum(_loginID,newTelPhone);
	}

	public String searchPerson(String name) throws UnknownAgentException{
		return _school.searchPerson(name);
	}

	public String showPerson(){
		return _school.showPerson(_loginID);
	}

	public String showAllPersons(){
		return _school.showAllPersons();
	}



	public void doSave(String fileName){
		if ((_initial==true) || (_ischanged==true)){
			// System.out.println(_initial);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
				oos.writeObject(_school);
				oos.close();
				_initial=false;
				_ischanged=false;
				System.out.println("Saved");

			}
			catch (IOException e) { e.printStackTrace(); }
		}

	}

	public boolean getChanged(){
		return _ischanged;
	}
	public void setChanged(){
		_ischanged=true;
	}
	public boolean getInitial(){
		return _initial;
	}
	public void setInitial(){
		_initial=false;
	}

	public int getLoginId(){
		return _loginID;
	}

	public void doOpen(String fileName) throws FileNotFoundException,ClassNotFoundException,IOException,NoSuchPersonIdException{
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			_schoolCurrent=_school;
			_school = (School)ois.readObject();
			ois.close();
		}
		catch (IOException            e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }

		finally {
			try{
				hasUser(_loginID);
			}
			catch (NoSuchPersonIdException e) {
					_school=_schoolCurrent;
					throw new NoSuchPersonIdException(_loginID);
			}
		}


	}

	/*=====================================
	=   Funções de Portal DOCENTE         =
	=====================================*/
	//4.1
	public void createProject(String disciplineName,String projectName) throws DuplicateProjectNewException,NoSuchDisciplineNewException{

		_school.createProject( _loginID,  disciplineName, projectName);
	}
	//4.2
	public void closeProject(String disciplineName,String projectName) throws NoSuchProjectNewException,NoSuchDisciplineNewException{

		_school.closeProject( _loginID, disciplineName, projectName);
	}
	//4.3
	public String showDisciplineStudents(String disciplineName) throws NoSuchDisciplineNewException{
		return _school.showDisciplineStudents(_loginID,disciplineName);
	}
	//4.4
	public String showProjectSubmissions(String disciplineName,String projectName) throws NoSuchProjectNewException,NoSuchDisciplineNewException{
		return _school.showProjectSubmissions(_loginID,disciplineName,projectName);
	}
	//4.5
	public String showSurveyResults(String disciplineName,String projectName) throws NoSuchProjectNewException, NoSurveyNewException, NoSuchDisciplineNewException{
		return "_school.showSurveyResults()";
	}

	/*=====================================
	=   Funções de Portal ALUNO           =
	=====================================*/
	//5.1
	public void deliverProject(String disciplineName, String projectName,String _description)throws NoSuchProjectNewException, NoSuchDisciplineNewException {
		_school.deliverProject(_loginID,disciplineName,projectName,_description);
	}
	//5.2
	public String answerSurvey(String disciplineName,String projectName) throws NoSuchProjectNewException,NoSurveyNewException,NoSuchDisciplineNewException{
		return _school.answerSurvey();
	}
	//5.3
	public String showSurveyResult(String disciplineName,String projectName) throws NoSuchProjectNewException,NoSurveyNewException,NoSuchDisciplineNewException{
		return _school.showSurveyResult();
	}
	/*=====================================
	=   Funções de Portal DELEGADO        =
	=====================================*/
	//6.1
	public String createSurvey(String disciplineName, String projectName) throws NoSuchProjectNewException, NoSuchDisciplineNewException, DuplicateSurveyNewException{
		try{
			return _school.createSurvey();
    }
    catch(DuplicateSurveyNewException e){
      throw new DuplicateSurveyNewException(disciplineName,projectName);
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectNewException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineNewException(disciplineName);
    }
	}
	//6.2
	public String cancelSurvey(String disciplineName, String projectName)throws NoSuchProjectNewException, NoSuchDisciplineNewException,SurveyFinishedNewException, NoSurveyNewException, NonEmptySurveyNewException{
		try{
			return _school.cancelSurvey();
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyNewException(disciplineName,projectName);
    }
    catch(NonEmptySurveyNewException e){
      throw new NonEmptySurveyNewException(disciplineName,projectName);
    }
    catch(SurveyFinishedNewException e){
      throw new SurveyFinishedNewException(disciplineName,projectName);
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectNewException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineNewException(disciplineName);
    }
	}
	//6.3
	public String openSurvey(String disciplineName, String projectName) throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, OpeningSurveyNewException{
		try{
			return _school.openSurvey();
    }
     catch(NoSurveyNewException e){
       throw new NoSurveyNewException(disciplineName,projectName);
     }
     catch(OpeningSurveyNewException e){
       throw new OpeningSurveyNewException(disciplineName,projectName);
     }
     catch(NoSuchProjectNewException e){
       throw new NoSuchProjectNewException(disciplineName,projectName);
     }
     catch(NoSuchDisciplineNewException e){
       throw new NoSuchDisciplineNewException(disciplineName);
     }
	}
	//6.4
	public String closeSurvey(String disciplineName, String projectName) throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, ClosingSurveyNewException{
		try{
			return _school.closeSurvey();
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyNewException(disciplineName,projectName);
    }
    catch(ClosingSurveyNewException e){
      throw new ClosingSurveyNewException(disciplineName,projectName);
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectNewException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineNewException(disciplineName);
    }
	}
	//6.5
	public String finishSurvey(String disciplineName, String projectName) throws NoSuchProjectNewException, NoSuchDisciplineNewException, NoSurveyNewException, FinishingSurveyNewException{
		 try{
			return _school.finishSurvey();
    }
    catch(NoSurveyNewException e){
      throw new NoSurveyNewException(disciplineName,projectName);
    }
    catch(FinishingSurveyNewException e){
      throw new FinishingSurveyNewException(disciplineName,projectName);
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectNewException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineNewException(disciplineName);
    }
	}
	//6.6
	public String showDisciplineSurvey(String disciplineName,String projectName)throws NoSuchProjectNewException, NoSuchDisciplineNewException{
		try{
			return _school.showDisciplineSurvey();
    }
    catch(NoSuchProjectNewException e){
      throw new NoSuchProjectNewException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineNewException e){
      throw new NoSuchDisciplineNewException(disciplineName);
    }
	}


}
