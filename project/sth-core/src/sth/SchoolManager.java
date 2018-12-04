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

import sth.exceptions.newexceptions.NoSurveyCoreException;
import sth.exceptions.newexceptions.ClosingSurveyCoreException;
import sth.exceptions.newexceptions.NoSuchProjectCoreException;
import sth.exceptions.newexceptions.NoSuchDisciplineCoreException;
import sth.exceptions.newexceptions.FinishingSurveyCoreException;
import sth.exceptions.newexceptions.OpeningSurveyCoreException;
import sth.exceptions.newexceptions.DuplicateSurveyCoreException;
import sth.exceptions.newexceptions.NonEmptySurveyCoreException;
import sth.exceptions.newexceptions.SurveyFinishedCoreException;
import sth.exceptions.newexceptions.DuplicateProjectCoreException;

//FIXME [FIXING-END] import other classes if needed

/**
* The façade class.
*/
public class SchoolManager {

	//FIXME add object attributes if needed

	//FIXME implement constructors if needed
	private School _school = new School();
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
		_initial=true;
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


	public void doOpen(String fileName) throws FileNotFoundException,ClassNotFoundException,IOException{
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			_school = (School)ois.readObject();
			ois.close();
		}
		catch (IOException            e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }



	}

	/*=====================================
	=   Funções de Portal DOCENTE         =
	=====================================*/
	//4.1
	public void createProject(String disciplineName,String projectName) throws DuplicateProjectCoreException,NoSuchDisciplineCoreException{

		_school.createProject( _loginID,  disciplineName, projectName);
	}
	//4.2
	public void closeProject(String disciplineName,String projectName) throws NoSuchProjectCoreException,NoSuchDisciplineCoreException{

		_school.closeProject( _loginID, disciplineName, projectName);
	}
	//4.3
	public String showDisciplineStudents(String disciplineName) throws NoSuchDisciplineCoreException{
		return _school.showDisciplineStudents(_loginID,disciplineName);
	}
	//4.4
	public String showProjectSubmissions(String disciplineName,String projectName) throws NoSuchProjectCoreException,NoSuchDisciplineCoreException{
		return _school.showProjectSubmissions(_loginID,disciplineName,projectName);
	}
	//4.5
	public String showSurveyResults(String disciplineName,String projectName) throws NoSuchProjectCoreException, NoSurveyCoreException, NoSuchDisciplineCoreException{
		return "_school.showSurveyResults()";
	}

	/*=====================================
	=   Funções de Portal ALUNO           =
	=====================================*/
	//5.1
	public String deliverProject(String disciplineName, String projectName,String _description)throws NoSuchProjectCoreException, NoSuchDisciplineCoreException {
		return _school.deliverProject();
	}
	//5.2
	public String answerSurvey(String disciplineName,String projectName) throws NoSuchProjectCoreException,NoSurveyCoreException,NoSuchDisciplineCoreException{
		return _school.answerSurvey();
	}
	//5.3
	public String showSurveyResult(String disciplineName,String projectName) throws NoSuchProjectCoreException,NoSurveyCoreException,NoSuchDisciplineCoreException{
		return _school.showSurveyResult();
	}
	/*=====================================
	=   Funções de Portal DELEGADO        =
	=====================================*/
	//6.1
	public String createSurvey(String disciplineName, String projectName) throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, DuplicateSurveyCoreException{
		try{
			return _school.createSurvey();
    }
    catch(DuplicateSurveyCoreException e){
      throw new DuplicateSurveyCoreException(disciplineName,projectName);
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectCoreException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineCoreException(disciplineName);
    }
	}
	//6.2
	public String cancelSurvey(String disciplineName, String projectName)throws NoSuchProjectCoreException, NoSuchDisciplineCoreException,SurveyFinishedCoreException, NoSurveyCoreException, NonEmptySurveyCoreException{
		try{
			return _school.cancelSurvey();
    }
    catch(NoSurveyCoreException e){
      throw new NoSurveyCoreException(disciplineName,projectName);
    }
    catch(NonEmptySurveyCoreException e){
      throw new NonEmptySurveyCoreException(disciplineName,projectName);
    }
    catch(SurveyFinishedCoreException e){
      throw new SurveyFinishedCoreException(disciplineName,projectName);
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectCoreException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineCoreException(disciplineName);
    }
	}
	//6.3
	public String openSurvey(String disciplineName, String projectName) throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, OpeningSurveyCoreException{
		try{
			return _school.openSurvey();
    }
     catch(NoSurveyCoreException e){
       throw new NoSurveyCoreException(disciplineName,projectName);
     }
     catch(OpeningSurveyCoreException e){
       throw new OpeningSurveyCoreException(disciplineName,projectName);
     }
     catch(NoSuchProjectCoreException e){
       throw new NoSuchProjectCoreException(disciplineName,projectName);
     }
     catch(NoSuchDisciplineCoreException e){
       throw new NoSuchDisciplineCoreException(disciplineName);
     }
	}
	//6.4
	public String closeSurvey(String disciplineName, String projectName) throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, ClosingSurveyCoreException{
		try{
			return _school.closeSurvey();
    }
    catch(NoSurveyCoreException e){
      throw new NoSurveyCoreException(disciplineName,projectName);
    }
    catch(ClosingSurveyCoreException e){
      throw new ClosingSurveyCoreException(disciplineName,projectName);
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectCoreException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineCoreException(disciplineName);
    }
	}
	//6.5
	public String finishSurvey(String disciplineName, String projectName) throws NoSuchProjectCoreException, NoSuchDisciplineCoreException, NoSurveyCoreException, FinishingSurveyCoreException{
		 try{
			return _school.finishSurvey();
    }
    catch(NoSurveyCoreException e){
      throw new NoSurveyCoreException(disciplineName,projectName);
    }
    catch(FinishingSurveyCoreException e){
      throw new FinishingSurveyCoreException(disciplineName,projectName);
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectCoreException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineCoreException(disciplineName);
    }
	}
	//6.6
	public String showDisciplineSurvey(String disciplineName,String projectName)throws NoSuchProjectCoreException, NoSuchDisciplineCoreException{
		try{
			return _school.showDisciplineSurvey();
    }
    catch(NoSuchProjectCoreException e){
      throw new NoSuchProjectCoreException(disciplineName,projectName);
    }
    catch(NoSuchDisciplineCoreException e){
      throw new NoSuchDisciplineCoreException(disciplineName);
    }
	}


}
