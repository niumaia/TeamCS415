/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import librarysystem.BookCopy;
import librarysystem.Student;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author Shabnil
 */
public class StudentDA {
    private dbConnect dbConn;
    Connection db;
            private static StrongPasswordEncryptor cred = new StrongPasswordEncryptor();

    public StudentDA() throws Exception {
        dbConn = new dbConnect("Student");
        db = (Connection) dbConn.getStudentConnector();
    }
    
    public Student getStudentDetails(String studentID)throws Exception{
        PreparedStatement myStmt = null;
        String sql = "select * from studentinfo where stud_id = ?";
        ResultSet myRs = null;

        try {            
            myStmt = db.prepareStatement(sql);   
            
            myStmt.setString(1,studentID);
            myRs = myStmt.executeQuery();
            String a = "",b= "",c= "",d= "",e= "",f= "",g= "";
            
            while (myRs.next()){            
                a = myRs.getString("stud_id");
                b = myRs.getString("stud_name");
                c = myRs.getString("stud_address");
                d = myRs.getString("stud_telephone");
                e = myRs.getString("stud_program");
                f = myRs.getString("stud_status");
                g = myRs.getString("stud_majors");
               
            }
            return new Student(a,b,c,d,e,f,g);
        } finally {
          //  db.close();
        }       
    }
    
    
    private String getEncrpytedPassword(String id) throws Exception {
		String encryptedPassword = null;
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
                String sql = "select stud_password from studentinfo where stud_id=?";
		
		try {
			myStmt = db.prepareStatement(sql);
                        myStmt.setString(1, id);
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				encryptedPassword = myRs.getString("stud_password");
			}
			else {
				throw new Exception("User id not found: " + id);
			}
                            //    System.out.println(encryptedPassword);
			return encryptedPassword;		
		}
		finally {
			db.close();
		}		
	}
    
    public boolean authenticate(Student theUser) throws Exception {
		boolean result = false;
		
		String plainTextPassword = theUser.getPassword();
		
		// get the encrypted password from database for this user
		String encryptedPasswordFromDatabase = getEncrpytedPassword(theUser.getStudentID());
         
		// compare the passwords
		result = cred.checkPassword(plainTextPassword, encryptedPasswordFromDatabase);
		
		return result;
	}
}
