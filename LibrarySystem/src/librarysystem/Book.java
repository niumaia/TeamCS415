package librarysystem;

public class Book {
	
	
	private int bkid;
	private String title;
	private String author;
	private String publisher;
	private String catalogNum;

	
	public Book(String title, String author, String publisher, String catalogNum ) {

		this(0,title, author, publisher, catalogNum);
	}
	
	public Book(int id, String title, String author, String publisher, String catalogNum) {
		super();
		this.bkid = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.catalogNum = catalogNum;
	}

	public int getBookId() {
		return bkid;
	}

	public void setBookId(int i) {
		this.bkid = i;
	}

	public String getCatalog() {
		return catalogNum;
	}

	public void setCatalog(String s) {
		this.catalogNum = s;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String s) {
		this.title = s;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}

	public String getpublisher() {
		return publisher;
	}

	public void setpublisher(String publisher) {
		this.publisher = publisher;
	}


	

	
	
}
