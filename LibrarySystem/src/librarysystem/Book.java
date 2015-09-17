package librarysystem;

public class Book {
	
	
	private int bkid;
	private String title;
	private String author;
	private String publisher;
	private String isbn;
        private String edition;
        private int bookcopies = 0;

	
	public Book(String title, String author, String publisher, String isbn,String edn ) {

		this(0,title, author, publisher, isbn,edn);
	}
	
	public Book(int id, String title, String author, String publisher, String isbn,String edn) {
		super();
		this.bkid = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
                this.edition = edn;
	}
        
        public int getBookId() {
		return bkid;
	}

	public void setBookId(int i) {
		this.bkid = i;
	}

	public int getCopies() {
		return bookcopies;
	}

	public void setCopies(int i) {
		this.bookcopies = i;
	}
        
        public String getisbn() {
		return isbn;
	}

	public void setisbn(String s) {
		this.isbn = s;
	}

         public String getedition() {
		return edition;
	}

	public void setedition(String s) {
		this.edition = s;
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
