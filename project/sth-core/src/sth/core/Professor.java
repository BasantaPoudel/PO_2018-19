package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

import java.text.Collator;
import java.util.Locale;

public class Professor extends Person implements Serializable{


    // private transient Locale locale = new Locale("pt", "PT");
    // private transient Collator  = Collator.getInstance(locale);

    //note <courseName<disciplineName,discipline>>
    private TreeMap<String,	TreeMap<String,	Discipline>> _disciplines = new TreeMap<String,	TreeMap<String,	Discipline>>();

    // <disciplineName,courseName >
    private TreeMap<String,	String> _disciplinesCourses = new TreeMap<String,	String>();

    private TreeMap<String,Discipline> disciplinesMap = new TreeMap<String,Discipline>(); // create a map of disciplineName's and disciplines for that course

    private TreeMap<String,Course> _courses =new TreeMap<String, Course>();
    // constructor

    public Professor(String _name,int _phoneNumber,int _id){
        super(_name,_phoneNumber,_id);
    }

    /*===========================================
    =            disciplines
    ===========================================*/
    public void putDiscipline(Discipline discipline){
        // names
        String courseName=discipline.getCourseName();

        String disciplineName=discipline.getName(); // get the disciplineName form discipline
        // System.out.println("Professor puts Discipline with  DisciplineName: "+disciplineName);
        // if teacher never had discipline of that course
        if (_disciplines.get(courseName) == null){
            disciplinesMap.put(disciplineName,discipline);  // put in map disciplineName and discipline
            _disciplines.put(courseName,disciplinesMap);  // add the new map  to respective course
        }
        // if teacher has had discipline of that course before
        else if (_disciplines.get(courseName) != null){
            // add  existing Map
            _disciplines.get(courseName).put(disciplineName,discipline);
        }
        _disciplinesCourses.put(disciplineName,courseName);// add to _disciplinesCourses (a Map<String,String>) the names
    }

    public void putCourse(String courseName, Course course){
      _courses.put(courseName,course);
    }

    public String getDisciplineName(String disciplineName){
        return _disciplinesCourses.get(disciplineName);
    }

    public Discipline getDiscipline(String disciplineName){
        return disciplinesMap.get(disciplineName);
    }

    //Overloading
    public Discipline getDiscipline(String courseName,String disciplineName){
       return _disciplines.get(courseName).get(disciplineName);
   }

    public boolean hasDiscipline(String disciplineName){
        return (disciplinesMap.containsKey(disciplineName));
    }

    public String getDisciplineCourseName(String disciplineName){
        return _disciplinesCourses.get(disciplineName);
    }


    /*===========================================
    =            show
    ===========================================*/

    public String show(){
        return "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
    }
    public String showWithDisciplines(){
        String res = "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
        for (String courseName : _disciplines.keySet() ) {
            for (String disciplineName : _disciplines.get(courseName).keySet() ){
                res+="*" + courseName +" - "+disciplineName+"\n";
            }
        }
        return res;
    }





    public String showDisciplineStudents(String disciplineName){
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
        String res="";
        Discipline disc = getDiscipline(disciplineName);
        // System.out.println(disc); //[debug]
        // System.out.println(disciplinesMap.size());//  [debug]

        String _allDisciplines = "";
        for (Map.Entry<String, Discipline> entry : disciplinesMap.entrySet()) {
          Discipline value = entry.getValue();
          String _sName =value.getName();
          _allDisciplines += _sName;
          // System.out.println("reached here show students in discipline");//  [debug]
          // System.out.println(_sName);//  [debug]
        }




        return res; //+ disc.showStudents();
    }




}
