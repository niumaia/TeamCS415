/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;
import java.util.Date;
/**
 *
 * @author tabunakawai_n
 */
public class Reserve {
    
    
	private int id;
        private int bookcopyid;
	private String title;
	private String author;
	private String catalog;
	private String studentid;       
        private Date reserveDate;
        private Date pickupDate;
        

	
	public Reserve(int bookcopyid,String title, String author, String catalog, String studentid,Date rdate,Date pdate ) {

		this(0,bookcopyid,title, author, catalog, studentid,rdate,pdate);
	}
	
	public Reserve(int id,int bookcopyid, String title, String author, String cat, String stud,Date rdate,Date pdate ) {
		super();
		this.id = id;
                this.bookcopyid = bookcopyid;
		this.title = title;
		this.author = author;
		this.catalog = cat;
		this.studentid = stud;
                this.reserveDate = rdate;
                this.pickupDate = pdate;
	}
        
        public int getReserveId() {
		return id;
	}

	public void setReserveId(int i) {
		this.id = i;
	}
        
        public int getBookCopyId() {
		return bookcopyid;
	}

	public void setBookCopyId(int i) {
		this.bookcopyid = i;
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
        
        public String getStudentID() {
		return studentid;
	}

	public void setStudentID(String s) {
		this.studentid = s;
	}

       public String getcatalogNo() {
		return catalog;
	}

	public void seticatalogNo(String s) {
		this.catalog = s;
	}
    	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date d) {
		this.reserveDate = d;
	}
        
        public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date d) {
		this.pickupDate = d;
	}
}
