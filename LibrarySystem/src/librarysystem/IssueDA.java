package librarysystem;

import java.sql.PreparedStatement;
import java.util.Date;
import java.text.DateFormat;
import librarysystem.Issue;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;

public class IssueDA {

    private dbConnect dbConn;
    Connection db;

    public IssueDA() throws Exception {
       dbConn = new dbConnect("Staff");
        db = (Connection) dbConn.getStaffConnector();
    }

    public Date getDateVal(DateFormat dateFormat, String s) throws Exception {

        Date theDate = null;
        String stripped = null;

        String info = s;

        // remove the mask characters
        if (info != null) {
            stripped = info.replaceAll("/", "");
        }

        // check for valid date
        if (stripped != null && stripped.trim().length() > 0) {
            theDate = (Date) dateFormat.parse(info);
        } else {
            theDate = null;
        }

        return theDate;
    }
    
    public Issue getBookIssue(int bookcopyId) throws Exception
    {
        PreparedStatement myStmt = null;        
        String sql = "select i.issueID,i.bkCopyID,i.StudentID,i.issueDueDate,i.checkOutDate,i.issueFine from issue i,bookcopy c where " +
                        "i.bkCopyID = c.bkCopyID and i.bkCopyID=? and c.copystat_id = 2";
        ResultSet myRs = null;
        Issue issue = null;
        
        
        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setInt(1, bookcopyId);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
            int issueId = myRs.getInt("issueID");
            int copyId = myRs.getInt("bkCopyID");
            int fine = myRs.getInt("issueFine");
            String studentID = myRs.getString("StudentID");
            java.sql.Date DateOut = myRs.getDate("checkOutDate");
            java.sql.Date DateDue = myRs.getDate("issueDueDate");
            

             issue = new Issue(issueId, copyId,studentID, DateOut, DateDue, 0, fine);
            }
         return issue;
        }finally {
            db.close();
        }      
    }

    public void checkOut(BookCopy bc, String studentid, Date out, Date due) throws Exception {
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

        } finally {
         //   db.close();
        }
    }
    
    public Double getTotalFines(String Id) throws Exception {
        PreparedStatement myStmt = null;       
        String sql = "select sum(l.amount) total from transaction t,line_item l where t.StudentID=? and l.transaction_id = t.transaction_id and t.complete = false";
        ResultSet myRs = null;
        Double totalFine = 0.00;
        
        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);
            myStmt.setString(1, Id);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
            totalFine = myRs.getDouble("total");
         
            }
            return totalFine;

        } finally {
         //   db.close();
        }
    }
   
    
    public void checkIn(BookCopy bc, String studentid, Date in,int due, double fine) throws Exception {
        PreparedStatement myStmt = null;
        java.sql.Date DateIn = null;
        
        String sql = "update issue set checkInDate=?,issueFine=?,dueID=? where bkCopyID=? and StudentID=?";

        try {
            // prepare statement
            myStmt = db.prepareStatement(sql);
            // set params
            if (in != null) {
                DateIn = new java.sql.Date(in.getTime());
            }
            myStmt.setDate(1, DateIn);
            myStmt.setDouble(2, fine);
            myStmt.setInt(3, due);
            myStmt.setInt(4, bc.getId());
            myStmt.setString(5, studentid);

			// execute SQL
            myStmt.executeUpdate();

        } finally {
            db.close();
        }
    }
    
}
