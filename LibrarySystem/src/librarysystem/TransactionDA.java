package librarysystem;

import com.mysql.jdbc.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDA {

    private dbConnect dbConn;
    Connection db;

    public TransactionDA() throws Exception {
       dbConn = new dbConnect("Staff");
       db = (Connection) dbConn.getStaffConnector();

    }

     public List<LineItem> getStudentFines(String studentID) throws Exception {
        List<LineItem> items = new ArrayList<LineItem>();
        PreparedStatement myStmt = null;
        String sql = "select b.bk_title,b.bk_author,c.bkCatnum,datediff(i.checkIndate , i.issueduedate) days ,sum(l.amount) amount,t.transaction_id,l.account_id,i.issueduedate" +
                        " from issue i,bookcopy c,books b,transaction t,line_item l where t.studentid =?"+
                        " and i.studentid = t.studentid and i.bkcopyid = c.bkcopyid and b.bk_id =c.bk_id"+
                        " and c.bkcatnum = t.reference_id and l.transaction_id = t.transaction_id and t.complete = false group by t.transaction_id";

        ResultSet myRs = null;

        try {
            myStmt = db.prepareStatement(sql);
            myStmt.setString(1, studentID);
   
            myRs = myStmt.executeQuery();

            while (myRs.next()) {

                int transactionnid = myRs.getInt("transaction_id");
                int numdays = myRs.getInt("days");
                int acct = myRs.getInt("account_id");                
                String title = myRs.getString("bk_title");
                String author = myRs.getString("bk_author");
                String catalog = myRs.getString("bkCatNum"); 
                java.sql.Date datedue = myRs.getDate("issueduedate");
                Double fine = myRs.getDouble("amount");

                LineItem temp = new LineItem(0,transactionnid,acct, "", title, author, catalog, numdays, fine,datedue);
                items.add(temp);
            }

            return items;
        } finally {
            db.close();
        }
    }
    
public void addTransactionLine(int tid,int account,Double amount,String desc,String ref,Date d1) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "insert into line_item (transaction_id,account_id,amount,description,reference,paydate) values (?, ?, ?, ?, ?,?)";
        java.sql.Date pdate = null;
        
        try {
            // prepare statements
               myStmt = db.prepareStatement(sql);
            if (d1 != null) {
                pdate = new java.sql.Date(d1.getTime());
            }

            // set params
            myStmt.setInt(1, tid);
            myStmt.setInt(2, account);
            myStmt.setDouble(3,amount);
            myStmt.setString(4, desc);
            myStmt.setString(5, ref);
            myStmt.setDate(6, pdate);

            // execute SQL			
           myStmt.executeUpdate();
            
        } finally {
            //DAOUtils.close(myStmt);
          //  db.close();
        }
    }


    public int addTransaction(Transaction t) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "insert into transaction (date,description,reference_id,studentID) values (?, ?, ?, ?)";
        java.sql.Date transdate = null;        
        ResultSet myRs = null;
       
        try {
            // prepare statements
            myStmt = db.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            if (t.getDate() != null) {
                transdate = new java.sql.Date(t.getDate().getTime());
            }

            // set params
            myStmt.setDate(1, transdate);
            myStmt.setString(2, t.getDescription());
            myStmt.setString(3, t.getReferenceId());
            myStmt.setString(4, t.getStudentID());

            // execute SQL			
     
           myStmt.executeUpdate();
           myRs = myStmt.getGeneratedKeys();
           myRs.next(); 
           int tid = myRs.getInt(1);
            return tid;
            
        } finally {
            //DAOUtils.close(myStmt);
           // db.close();
        }
    }


public void completeTransaction(int transactionid) throws Exception {
        PreparedStatement myStmt = null;
        String sql = "update transaction set complete=1 where transaction_id =?";
        
        try {
            // prepare statements
            myStmt = db.prepareStatement(sql);           
           
            myStmt.setInt(1 ,transactionid);
            myStmt.executeUpdate();
            
        } finally {
            //DAOUtils.close(myStmt);
            db.close();
        }
    }

}