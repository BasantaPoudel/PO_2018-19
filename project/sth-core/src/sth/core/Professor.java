    package sth.core;
    import java.util.Map;
    import java.util.TreeMap;
    import java.util.HashMap;
    import java.io.Serializable;

    import java.text.Collator;
    import java.util.Locale;
    import java.util.Comparator;

    public class Professor extends Person implements Serializable{

      private transient Locale locale = new Locale("pt", "PT");
      private transient Collator collator = Collator.getInstance(locale);

      private transient TreeMap<String,	TreeMap<String,	Discipline>> _disciplinesTreeMap = new TreeMap<String,	TreeMap<String,	Discipline>>(collator);
      private HashMap<String,	HashMap<String,	Discipline>> _disciplinesHashMap = new HashMap<String,	HashMap<String,	Discipline>>();

      public Professor(String _name,int _phoneNumber,int _id){
        super(_name,_phoneNumber,_id);
      }

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
      public void addDiscipline(Discipline discipline){

        // names
        String courseName=discipline.getCourseName();
        String disciplineName=discipline.getName();

        if (_disciplinesHashMap.get(courseName) == null){


          TreeMap<String,Discipline> disciplinesMap = new TreeMap<String,Discipline>();  // new Map
          HashMap<String,Discipline> disciplinesHMap = new HashMap<String,Discipline>();  // new Map

          disciplinesMap.put(disciplineName,discipline);
          _disciplinesTreeMap.put(courseName,disciplinesMap);
          _disciplinesHashMap.put(courseName,disciplinesHMap);

        }
        else if (_disciplinesHashMap.get(courseName) != null){

          _disciplinesTreeMap.get(courseName).put(disciplineName,discipline); // existing Map
          _disciplinesHashMap.get(courseName).put(disciplineName,discipline); // existing Map

        }


      }

      public void TreeToHashMap(){

        // empty _disciplinesHashMap
        _disciplinesHashMap = new HashMap<String,	HashMap<String,	Discipline>>();


        // for each course in TreeMap
        for (String courseName: _disciplinesTreeMap.keySet()){
          HashMap<String,	Discipline> discs=new HashMap<String,	Discipline>();
          // for each disciplineName in curr course map of disciplines
          for (String disciplineName: _disciplinesTreeMap.get(courseName).keySet()) {

            discs.put(disciplineName,_disciplinesTreeMap.get(courseName).get(disciplineName));

          }

          _disciplinesHashMap.put(courseName,discs);



        }
      }

      public void ReverseTreeToHashMap(){

        // empty _disciplinesHashMap
        _disciplinesTreeMap = new TreeMap<String,	TreeMap<String,	Discipline>>();


        // for each course in TreeMap
        for (String courseName: _disciplinesHashMap.keySet()){
          TreeMap<String,	Discipline> discs=new TreeMap<String,	Discipline>();
          // for each disciplineName in curr course map of disciplines
          for (String disciplineName: _disciplinesHashMap.get(courseName).keySet()) {

            discs.put(disciplineName,_disciplinesHashMap.get(courseName).get(disciplineName));

          }

          _disciplinesTreeMap.put(courseName,discs);



        }
      }

        public void createProject(String proj,String disc){
        }
        public void closeProject(String proj,String disc){
        }

        public String show(){

          return "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";

        }

        public String showWithDisciplines(){


          TreeToHashMap();
          ReverseTreeToHashMap();
          String res = "DOCENTE"+"|"+getId()+"|"+getPhoneNumber()+"|"+getName()+"\n";
          for (String courseName : _disciplinesTreeMap.keySet() ) {
            for (String disciplineName : _disciplinesTreeMap.get(courseName).keySet() ){
              res=res+"*" + courseName +" - "+disciplineName+"\n";
            }
          }
          return res;
        }




      }
