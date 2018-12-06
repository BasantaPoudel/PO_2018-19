package sth.core;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class ProjectSubmission implements Serializable{

	private String _content;

	public ProjectSubmission(String content){

		_content=content;
	}

	public String show (){
		return _content;
		// Formato de apresentação
		// * Identificador do 1º aluno - submissão do 1º aluno
		// ...
		// * Identificador do Nº aluno - submissão do Nº aluno
		// Exemplo de apresentação
		// Programação com Objectos - Gatos Simples
		// * 0234 - Gato.java
		// * 6789 - Cat.java
		// * 7912 - Tigre.java


	}


}
