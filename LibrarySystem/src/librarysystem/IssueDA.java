package librarysystem;

import java.sql.PreparedStatement;
import java.util.Date;

import com.mysql.jdbc.Connection;



public class IssueDA {

	
	private dbConnect dbConn ;
	Connection db ;
	
	public IssueDA() throws Exception
	{
		dbConn = new dbConnect();
		db = (Connection)dbConn.getConnector();
	}
	
	public void checkOut(BookCopy bc,String studentid,Date out,Date due) throws Exception {
		PreparedStatement myStmt = null;
		java.sql.Date DateOut = null;
		java.sql.Date DateDue = null;
		String sql = "insert into issue (bkCopyID,checkOutDate,issueDueDate,dueId,StudentID,issueFine) values(?,?,?,2,?,0)";

		try {
			// prepare statement
			myStmt = db.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, bc.getId());			
			
			if (out != null) {
				DateOut = new java.sql.Date(out.getTime());
			}
			
			if (due != null) {
				DateDue = new java.sql.Date(due.getTime());
			}
			
			myStmt.setDate(2, DateOut);
			myStmt.setDate(3, DateDue);
			myStmt.setString(4, studentid);
			
			// execute SQL
			
			myStmt.executeUpdate();
			bc.setstatus(2);

		}
		finally {
			db.close();
		}		
	}
}
