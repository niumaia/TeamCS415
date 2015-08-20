package librarysystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;


public class BookCopyDA {

	private dbConnect dbConn ;
	Connection db ;
	
	public BookCopyDA() throws Exception
	{
		dbConn = new dbConnect();
		db = (Connection)dbConn.getConnector();
	}
	
	public List<BookCopy> findBooks(String title, String author, String cat,String isbn) throws Exception {
		List<BookCopy> blist = new ArrayList<BookCopy>();		
		PreparedStatement myStmt = null;
		String sql ="select c.bkCopyID,c.bkCopyNum,b.bk_title,b.bk_author,b.bk_catNum,c.ISBN,s.copyStat_desc from books b ,bookcopy c,copy_status s"
				+ " where c.bk_id = b.bk_id and s.copyStat_id = c.copyStat_id "
				+ " and b.bk_title like ? and b.bk_author like ? and b.bk_catNum like ? and c.ISBN like ? ";				
		
		ResultSet myRs = null;
		
		try {
			myStmt = db.prepareStatement(sql);	
			myStmt.setString(1, "%" + author + "%");			
			myStmt.setString(2, "%" + author + "%");
			myStmt.setString(3, "%" + cat + "%");
			myStmt.setString(4, "%" + isbn + "%");
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				
				int bcid = myRs.getInt("bkCopyID");
				int copy = myRs.getInt("bkCopyNum");
				String btitle = myRs.getString("bk_title");
				String bauthor = myRs.getString("bk_author");
				String bcat = myRs.getString("bk_catNum");
				String bisbn = myRs.getString("ISBN");
				String bstat = myRs.getString("copyStat_desc");
				
				BookCopy tempBk = new BookCopy(bcid,copy, btitle, bauthor, bcat, bisbn, bstat);			
				blist.add(tempBk);
			}

			return blist;		
		}
		finally {
			db.close();
		}
	}
}
