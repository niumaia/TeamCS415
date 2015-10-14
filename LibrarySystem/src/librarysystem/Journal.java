package librarysystem;

public class Journal {
	
	private int Id;
	private String year;
	private String title;
	private String authors;
	private String publication;
	private String isbn;
        private String textabstract;


	
	//public Journal(String year,String title, String authors, String publication, String isbn,String abs ) {

	//	this(year,title, authors, publication, isbn,abs);
	//}
            
	public Journal(){
	//	super();
            this(0,"","","","","","");
       }
	
	public Journal(int id,String year, String title, String authors, String publication, String isbn,String abs) {
		super();
                this.Id = id;
		this.year = year;
		this.title = title;
		this.authors = authors;
		this.publication = publication;
		this.isbn = isbn;
                this.textabstract = abs;
	}
        
        public int getID() {
		return Id;
	}

	public void setID(int i) {
		this.Id = i;
	}
        
        public String getYear() {
		return year;
	}

	public void setYear(String y) {
		this.year = y;
	}

	public String getTitle() {
		return title;
	}

	public void getTitle(String t) {
		this.title = t;
	}
        
        public String getisbn() {
		return isbn;
	}

	public void setisbn(String s) {
		this.isbn = s;
	}

         public String getAbstract() {
		return textabstract;
	}

	public void setAbstract(String abs) {
		this.textabstract = abs;
	}
        

	public String gettitle() {
		return title;
	}

	public void settitle(String s) {
		this.title = s;
	}

	public String getauthors() {
		return authors;
	}

	public void setauthors(String author) {
		this.authors = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String p) {
		this.publication = p;
	}


	

	
	
}
