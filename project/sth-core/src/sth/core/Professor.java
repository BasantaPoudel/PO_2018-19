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
        for (String courseName : _courses.keySet() ) {
              res +=_courses.get(courseName).showDisciplines(disciplinesMap);
        }
          // System.out.println(res);
        return res;
    }

    public String showDisciplineStudents(String disciplineName){
        String res="";
        String cName= getDisciplineCourseName(disciplineName);
        Course c = _courses.get(cName);
        Discipline disc = c.getDiscipline(disciplineName);
        res+=disc.showStudents();
        return res; //+ disc.showStudents();
    }




}
