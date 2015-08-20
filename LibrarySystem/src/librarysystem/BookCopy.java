package librarysystem;

import java.util.Date;

public class BookCopy {
	
	private int id;
	private int bookid;
	private int copy;
	private String edition;
	private Date year;
	private String isbn;
	private int status;
	
	private String title; 
	private String author; 
	private String cat; 
	private String desc;


	public BookCopy(int bookid,int copy, String edition, Date year, String isbn,int status ) {

		this(0, bookid, copy, edition,year, isbn,status);
	}
	
	public BookCopy(int id, int bookid, int copy, String edition, Date year, String isbn,int status){
		super();
		this.id = id;
		this.bookid = bookid;
		this.copy = copy;
		this.year = year;
		this.status = status;
		this.isbn = isbn;
		this.edition = edition;
	}
	
	public BookCopy(int bookid,int copy, String title, String author,String cat, String isbn, String desc){
		super();

		this.id = bookid;
		this.title = title;
		this.author = author;
		this.desc = desc;
		this.isbn = isbn;
		this.cat = cat;
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
	
	public String getcat() {
		return cat;
	}
	
	public String getdesc() {
		return desc;
	}
	
	
	public String getedition() {
		return edition ;
	}
	
	public void setedition(String ed) {
		this.edition = ed;
	}

	public String getisbn() {
		return isbn;
	}

	public void setisbn(String isbn) {
		this.isbn = isbn;
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
