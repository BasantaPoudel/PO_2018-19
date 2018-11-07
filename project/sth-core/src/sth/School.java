package sth;

//FIXME [FIXING-BEGIN] import other classes if needed
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

//[FIXING-END]
import sth.exceptions.BadEntryException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)

  //FIXME implement constructors if needed

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    //FIXME [FIXING-BEGIN] implement text file reader
   try {
     String s = new String();
     BufferedReader in = new BufferedReader(new FileReader(filename));
     PrintWriter   out = new PrintWriter(new BufferedWriter(new FileWriter("Escritor1.out")));
     int lineCount = 1;
     while((s = in.readLine()) != null ){
                     String[] fields = s.split("\\|");
                   for (String i:fields){
                     System.out.println(i);
                   }
          out.printf("%3d: %s\n", lineCount++, s);
          }
          out.close();
     in.close();
   }
   catch(IOException e) { System.err.println("Erro Ler o Ficheiro"); }
   //catch(BadEntryException e) { System.err.println("Erro Ler o Ficheiro"); }
   //[FIXING-END]
  }

  //FIXME implement other methods

}
