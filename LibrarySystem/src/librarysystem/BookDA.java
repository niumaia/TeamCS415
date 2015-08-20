package librarysystem;

import com.mysql.jdbc.Connection;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BookDA {
	
	private dbConnect dbConn ;
	Connection db ;
	
	public BookDA() throws Exception
	{
		dbConn = new dbConnect();
		db = (Connection)dbConn.getConnector();
		
		
	}
	
	public void addBook(Book b) throws Exception {
		PreparedStatement myStmt = null;
		String sql = "insert into users (first_name, last_name, email, is_admin, password) values (?, ?, ?, ?, ?)";

		try {
			// prepare statements
			myStmt =  db.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, b.getauthor());
			myStmt.setString(2, b.gettitle());
			myStmt.setString(3, b.getpublisher());
			myStmt.setString(4, b.getCatalog());
			
			// execute SQL			
		 myStmt.executeUpdate();				
		}
		finally {
			//DAOUtils.close(myStmt);
			db.close();
		}
		
	}
	
	
	public void updateBook(Book b) throws Exception {
		PreparedStatement myStmt = null;
		String sql = "update users set first_name=?, last_name=?, email=?, is_admin=? where id=?";

		try {
			// prepare statement
			myStmt = db.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, b.getauthor());
			myStmt.setString(2, b.gettitle());
			myStmt.setString(3, b.getpublisher());
			myStmt.setString(4, b.getCatalog());
	
			
			// execute SQL
			myStmt.executeUpdate();

		}
		finally {
			db.close();
		}		
	}
	
	public List<Book> searchBooks() throws Exception {
		List<Book> blist = new ArrayList<Book>();		
		PreparedStatement myStmt = null;
		String sql ="select * from books";
		ResultSet myRs = null;
		
		try {
			myStmt = db.prepareStatement(sql);			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				int id = myRs.getInt("bk_id");
				String title = myRs.getString("bk_title");
				String author = myRs.getString("bk_author");
				String publisher = myRs.getString("bk_publisher");
				String catalogNum = myRs.getString("bk_catNum");
				
				Book tempBk = new Book(id,title,author,publisher,catalogNum);				
				blist.add(tempBk);
			}

			return blist;		
		}
		finally {
			db.close();
		}
	}
	
	public Book getBook(int id) throws Exception {
		Book b = null;		
		PreparedStatement myStmt = null;
		String sql ="select * from books where bk_id = ?";
		ResultSet myRs = null;
		
		try {
			myStmt = db.prepareStatement(sql);		
			
			// prepare statement
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				int bkid = myRs.getInt("id");
				String title = myRs.getString("title");
				String author = myRs.getString("author");
				String publisher = myRs.getString("publisher");
				String catalogNum = myRs.getString("catalogNum");
				
				b = new Book(bkid,title,author,publisher,catalogNum);				
			
			}

			return b;		
		}
		finally {
			db.close();
		}
	}
	
	
}
