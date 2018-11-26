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

import sth.exceptions.newexceptions.NoSurveyexcepcao;
import sth.exceptions.newexceptions.ClosingSurveyexcepcao;
import sth.exceptions.newexceptions.NoSuchProjectexcepcao;
import sth.exceptions.newexceptions.NoSuchDisciplineexcepcao;
import sth.exceptions.newexceptions.FinishingSurveyexcepcao;
import sth.exceptions.newexceptions.OpeningSurveyexcepcao;
import sth.exceptions.newexceptions.DuplicateSurveyexcepcao;
import sth.exceptions.newexceptions.NonEmptySurveyexcepcao;
import sth.exceptions.newexceptions.SurveyFinishedexcepcao;
import sth.exceptions.newexceptions.DuplicateProjectexcepcao;

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
		// if (_ischanged=false){
		// XMLEncoder encoder=null;
		// try{
		// encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
		// }catch(FileNotFoundException fileNotFound){
		// 	System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		// }
		// encoder.writeObject(_school);
		// encoder.close();
		// // }
		// _ischanged=true;
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

		// XMLDecoder decoder=null;
		// try {
		// 	decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
		// } catch (FileNotFoundException e) {
		// 	System.out.println("ERROR: File not found");
		// }
		// School _school=(School)decoder.readObject();
		// System.out.println(_school);

	}
	// __________________________________________________________________________
	// try {
	//   ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("school.dat")));
	//   School _school = (School)ois.readObject();
	//   ois.close();
	// }
	// catch (IOException            e) { e.printStackTrace(); }
	// catch (ClassNotFoundException e) { e.printStackTrace(); }
	// // __________________________________________________________________________

	// __________________________________________________________________________//
	// __________________________________________________________________________//

	/*=====================================
	=   Funções de Portal DOCENTE         =
	=====================================*/
	//4.1
	public String createProject(String _disciplinename,String _projectname) throws DuplicateProjectexcepcao,NoSuchDisciplineexcepcao{
		return "_school.createProject()";
	}
	//4.2
	public String closeProject(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao,NoSuchDisciplineexcepcao{
		return "_school.closeProject()";
	}
	//4.3
	public String showDisciplineStudent(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao,NoSuchDisciplineexcepcao{
		return "_school.showDisciplineStudent()";
	}
	//4.4
	public String showProjectSubmissions(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao,NoSuchDisciplineexcepcao{
		return "_school.showProjectSubmissions()";
	}
	//4.5
	public String showSurveyResults(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao, NoSurveyexcepcao, NoSuchDisciplineexcepcao{
		return "_school.showSurveyResults()";
	}

	/*=====================================
	=   Funções de Portal ALUNO           =
	=====================================*/
	//5.1
	public String deliverProject(String _disciplinename, String _projectname,String _description)throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao {
		return _school.deliverProject();
	}
	//5.2
	public String answerSurvey(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao,NoSurveyexcepcao,NoSuchDisciplineexcepcao{
		return _school.answerSurvey();
	}
	//5.3
	public String showSurveyResult(String _disciplinename,String _projectname) throws NoSuchProjectexcepcao,NoSurveyexcepcao,NoSuchDisciplineexcepcao{
		return _school.showSurveyResult();
	}
	/*=====================================
	=   Funções de Portal DELEGADO        =
	=====================================*/
	//6.1
	public String createSurvey(String _disciplinename, String _projectname) throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, DuplicateSurveyexcepcao{
		try{
			return _school.createSurvey();
    }
    catch(DuplicateSurveyexcepcao e){
      throw new DuplicateSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineexcepcao(_disciplinename);
    }
	}
	//6.2
	public String cancelSurvey(String _disciplinename, String _projectname)throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao,SurveyFinishedexcepcao, NoSurveyexcepcao, NonEmptySurveyexcepcao{
		try{
			return _school.cancelSurvey();
    }
    catch(NoSurveyexcepcao e){
      throw new NoSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(NonEmptySurveyexcepcao e){
      throw new NonEmptySurveyexcepcao(_disciplinename,_projectname);
    }
    catch(SurveyFinishedexcepcao e){
      throw new SurveyFinishedexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineexcepcao(_disciplinename);
    }
	}
	//6.3
	public String openSurvey(String _disciplinename, String _projectname) throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, OpeningSurveyexcepcao{
		try{
			return _school.openSurvey();
    }
     catch(NoSurveyexcepcao e){
       throw new NoSurveyexcepcao(_disciplinename,_projectname);
     }
     catch(OpeningSurveyexcepcao e){
       throw new OpeningSurveyexcepcao(_disciplinename,_projectname);
     }
     catch(NoSuchProjectexcepcao e){
       throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
     }
     catch(NoSuchDisciplineexcepcao e){
       throw new NoSuchDisciplineexcepcao(_disciplinename);
     }
	}
	//6.4
	public String closeSurvey(String _disciplinename, String _projectname) throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, ClosingSurveyexcepcao{
		try{
			return _school.closeSurvey();
    }
    catch(NoSurveyexcepcao e){
      throw new NoSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(ClosingSurveyexcepcao e){
      throw new ClosingSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineexcepcao(_disciplinename);
    }
	}
	//6.5
	public String finishSurvey(String _disciplinename, String _projectname) throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao, NoSurveyexcepcao, FinishingSurveyexcepcao{
		 try{
			return _school.finishSurvey();
    }
    catch(NoSurveyexcepcao e){
      throw new NoSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(FinishingSurveyexcepcao e){
      throw new FinishingSurveyexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineexcepcao(_disciplinename);
    }
	}
	//6.6
	public String showDisciplineSurvey(String _disciplinename,String _projectname)throws NoSuchProjectexcepcao, NoSuchDisciplineexcepcao{
		try{
			return _school.showDisciplineSurvey();
    }
    catch(NoSuchProjectexcepcao e){
      throw new NoSuchProjectexcepcao(_disciplinename,_projectname);
    }
    catch(NoSuchDisciplineexcepcao e){
      throw new NoSuchDisciplineexcepcao(_disciplinename);
    }
	}


}
