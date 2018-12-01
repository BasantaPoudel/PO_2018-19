package sth.core;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

import java.text.Collator;
import java.util.Locale;

public class Professor extends Person implements Serializable{


    Locale locale = new Locale("pt", "PT");
    Collator collator = Collator.getInstance(locale);

    //note <courseName<disciplineName,discipline>>
    private TreeMap<String,	TreeMap<String,	Discipline>> _disciplines = new TreeMap<String,	TreeMap<String,	Discipline>>(collator);
    private TreeMap<String,	TreeMap<String,	String>> _disciplinesCourses = new TreeMap<String,	TreeMap<String,	String>>(collator);

    // constructor

    public Professor(String _name,int _phoneNumber,int _id){
        super(_name,_phoneNumber,_id);
    }

    // _______________________________________________________________________
    public void addDiscipline(Discipline discipline){

        // ============= names ===================

        String courseName=discipline.getCourseName();

        // get the disciplineName form discipline
        String disciplineName=discipline.getName();
        // ========================================

        // if teacher never had discipline of that course
        if (_disciplines.get(courseName) == null){

            // create a map of disciplineName's and disciplines for that course
            TreeMap<String,Discipline> disciplinesMap = new TreeMap<String,Discipline>(collator);

            // put in map disciplineName and discipline
            disciplinesMap.put(disciplineName,discipline);

            // add the new map  to respective course
            _disciplines.put(courseName,disciplinesMap);

        }

        // if teacher has had discipline of that course before
        else if (_disciplines.get(courseName) != null){
            // add  existing Map
            _disciplines.get(courseName).put(disciplineName,discipline);
        }
    }
    // _______________________________________________________________________



    public void setName(String name){
        super.setName(name);
    }
    public int getPhoneNumber(){
        return super.getPhoneNumber();
    }
    public void setPhoneNumber(int phoneNumber){
        super.setPhoneNumber(phoneNumber);
    }
    public int getId(){
        return super.getId();
    }
    public void setId(int id){
        super.setId(id);
    }
    public Discipline getDiscipline(Discipline d){
        return _disciplines.get(d.getCourseName()).get(d.getName());
    }





    public void createProject(String proj,String disc){
    }
    public void closeProject(String proj,String disc){
    }

    public String show(){

        return "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

    }

    public String showWithDisciplines(){

        String res = "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
        for (String courseName : _disciplines.keySet() ) {
            for (String disciplineName : _disciplines.get(courseName).keySet() ){
                res=res+"*" + courseName +" - "+disciplineName+"\n";
            }
        }
        return res;
    }




}
