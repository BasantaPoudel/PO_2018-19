public class Course{
	private String _name;
	private int _numOfRepresentatives=0;

	public Course(String name){
		_name=name;
		
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
	public int getNumOfRepresentatives(){
		return _numOfRepresentatives;
	}
	public void setNumOfRepresentatives(int numOfRepresentatives){
		_numOfRepresentatives=numOfRepresentatives;
	}
}