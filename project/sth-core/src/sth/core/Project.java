package sth.core;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import sth.exceptions.newexceptions.NoSuchDisciplineNewException;

public class Project implements Serializable{
	private String _name;

	private String _description;
	private String _content;

	private boolean _closed=false;

	// private Survey _survey; // = new TreeMap<String,Survey>();

	private Map<Integer,ProjectSubmission> _projectSubmissions = new TreeMap<Integer,ProjectSubmission>();
	// private Map<Integer,SurveySubmission> _projectSubmissions = new TreeMap<Integer,SurveySubmission>();

	public Project(String name){
		_name=name;
	}

	//getters
	public String getName(){
		return _name;
	}

	public void close(){
		_closed=true;
	}

	public boolean getState(){
		return _closed;
	}

	public void open(){
        _closed=false;
	}

	public boolean hasSubmission(Integer id){
		return (_projectSubmissions.containsKey(id));
	}

	public void submitProject(Integer id, ProjectSubmission submission){
		_projectSubmissions.put(id,submission);
	}

	public ProjectSubmission getProjectSubmitted(Integer id){
			return _projectSubmissions.get(id);
	}

	public String showSubmissions(){
		// for (Map.Entry<Integer, ProjectSubmission> entry : _projectSubmissions.entrySet()) {
		// 	ProjectSubmission value = entry.getValue();
		// 	_allSubmissions += value.show();
		//
		// 	// System.out.println("reached here show students in discipline");//  [debug]
		// }
		// System.out.println(_allSubmissions);

		// Formato de apresentação
		// * Identificador do 1º aluno - submissão do 1º aluno
		// ...
		// * Identificador do Nº aluno - submissão do Nº aluno
		// Exemplo de apresentação
		// Programação com Objectos - Gatos Simples
		// * 0234 - Gato.java
		// * 6789 - Cat.java
		// * 7912 - Tigre.java

		String res = "";
		for (Integer id : _projectSubmissions.keySet() ){
				 res+="\n* " + id + " - " + _projectSubmissions.get(id).show();
		}

		return res;
	}

	public void createSurvey(){

	}

	public void closeSurvey(){

	}

	public void cancelSurvey(){

	}

	public void finishSurvey(){

	}

	public void openSurvey(){

	}

}
