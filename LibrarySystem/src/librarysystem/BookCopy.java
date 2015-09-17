package librarysystem;

import java.util.Date;

public class BookCopy {
	
	private int id;
	private int bookid;
	private int copy;
	private String edition;
	private Date year;
	private String catalog;
	private int status;
	
	private String title; 
	private String author; 	 
	private String desc;


	public BookCopy(int bookid,int copy, String edition, Date year, String cat,int status,String desc ) {

		this(0, bookid, copy, edition,year, cat,status,desc);
	}
                    
	public BookCopy(int id, int bookid, int copy, String edition, Date year, String cat,int status,String desc){
		super();
		this.id = id;
		this.bookid = bookid;
		this.copy = copy;
		this.year = year;
		this.status = status;
		this.catalog = cat;
		this.edition = edition;
                this.desc = desc;
	}
	    
	public BookCopy(int bookid,int copyid,int copy, String title, String author,String cat, String edn, String desc){
		super();
		this.id = copyid;
                this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.desc = desc;
		this.catalog = cat;
		this.edition = edn;
		this.copy = copy;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getbookid() {
		return bookid;
	}

	public void setbookid(int id) {
		this.bookid = id;
	}

	public int getcopyNum() {
		return copy;
	}

	public void setcopyNum(int copy) {
		this.copy = copy;
	}

	
	public String gettitle() {
		return title;
	}

	public String getauthor() {
		return author;
	}
	
	public String getdesc() {
		return desc;
	}
        
        public void setdesc(String s) {
		this.desc = s;
	}
	
	
	public String getedition() {
		return edition ;
	}
	
	public void setedition(String ed) {
		this.edition = ed;
	}

	public String getcatalogNo() {
		return catalog;
	}

	public void seticatalogNo(String s) {
		this.catalog = s;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public int getstatus() {
		return status;
	}

	public void setstatus(int status) {
		this.status = status;
	}

	

	
	
}
