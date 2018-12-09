package sth.core;
import java.io.Serializable;
import java.util.Comparator;
import java.text.Collator;
import java.util.Locale;

public class Wrapper implements Comparator<String>, Serializable{
  private transient Locale _locale = new Locale("pt", "PT");
  private transient Collator _collator = Collator.getInstance(_locale);

  
  public int compare(String name, String nameC){
    return _collator.compare(name,nameC);
  }

}
