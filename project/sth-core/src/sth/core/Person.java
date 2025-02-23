package sth.core;
import java.io.Serializable;

public class Person implements Serializable{

	private String _name;
	private int _phoneNumber;
	private int _id;

	public Person(String name,int phoneNumber,int id){
		_name=name;
		_phoneNumber=phoneNumber;
		_id=id;
	}


	/*===========================================
	=            getters and setters            =
	===========================================*/
	public String getName(){
		return _name;
	}
	public void setName(String name){
		_name=name;
	}

	public int getPhoneNumber(){
		return _phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber){
		_phoneNumber=phoneNumber;
	}

	public int getId(){
		return _id;
	}
	public void setId(int id){
		_id=id;
	}

	public String show(){
		return "";
	}
	public String showWithDisciplines(){
		return "";
	}
	/*=====  End of getters and setters  ======*/


}
