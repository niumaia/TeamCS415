package librarysystem;

public class Overdue {
	
	private int id;
	private String desc;
	
	public Overdue (String desc){

		this(0,desc);
	}
	
	public Overdue (int id, String desc){
		super();
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String s) {
		this.desc = s;
	}

	
	

}
