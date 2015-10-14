/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import librarysystem.Book;
import librarysystem.Student;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shabnil
 */
public class studentbookDA {
    private dbConnect dbConn;
    Connection db;

    public studentbookDA() throws Exception {
        dbConn = new dbConnect("Staff");
        db = (Connection) dbConn.getStaffConnector();
    }
    
    public List <Issue> getCatBooks(String studentID)throws Exception{
        List<Issue> blist = new ArrayList<Issue>();
        PreparedStatement myStmt = null;
        String sql = "  select * , CASE WHEN  issueDuedate >=  curDate() THEN 'On Time'  WHEN  issueDuedate <  curDate() THEN 'Overdue' " +
                    "END as dueDesc ,datediff( curDate() , i.issueduedate) * 1.5  as fine   from books b, bookcopy c, issue i where c.bk_id = b.bk_id and i.bkcopyid = c.bkcopyid and " +
                    "  c.copystat_id = 2  and i.studentid = ?";
        ResultSet myRs = null;
            
        try {            
            myStmt = db.prepareStatement(sql);               
            myStmt.setString(1,studentID);
            myRs = myStmt.executeQuery();
            
            while (myRs.next()){            
                int id = myRs.getInt("bk_id");
                String title = myRs.getString("bk_title");
                String cat = myRs.getString("bkcatnum");
                String status = myRs.getString("dueDesc");
                String auth = myRs.getString("bk_author");
             //   int fine = myRs.getInt("issuefine");
                 double fine = myRs.getInt("fine");
                java.sql.Date cDate = myRs.getDate("checkOutDate"); 
                java.sql.Date dDate = myRs.getDate("issueDueDate"); 
                 
                Issue i = new Issue(id,title,auth,cat,status,dDate,cDate,fine);
                blist.add(i);
            }            
            //id, String t, String catalog, String st, Date dateDue,Date d, int fine
          return blist;        
        }catch(Exception e)
                {
                    e.printStackTrace();
                    return new ArrayList();
                }finally {
            db.close();
        }
    }
}