/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.PreparedStatement;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import librarysystem.Users;
/**
 *
 * @author tabunakawai_n
 */
public class UsersDA {
	private dbConnect dbConn ;
	Connection db ;
        private static StrongPasswordEncryptor cred = new StrongPasswordEncryptor();
	
	public UsersDA() throws Exception
	{
		dbConn = new dbConnect("Staff");
		db = (Connection)dbConn.getStaffConnector();
	}
        
 	public boolean authenticate(Users theUser) throws Exception {
		boolean result = false;
		
		String plainTextPassword = theUser.getPassword();
		
		// get the encrypted password from database for this user
		String encryptedPasswordFromDatabase = getEncrpytedPassword(theUser.getId());
		
		// compare the passwords
		result = cred.checkPassword(plainTextPassword, encryptedPasswordFromDatabase);
		
		return result;
	}
        
	public Users getUser(String uname) throws Exception {
		Users u = null;		
		PreparedStatement myStmt = null;
		String sql ="select id,username,email,role from users where username = ?";
		ResultSet myRs = null;
		
		try {
			myStmt = db.prepareStatement(sql);
			// prepare statement
			myStmt.setString(1, uname);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				
				int id = myRs.getInt("id");
				String username = myRs.getString("username");
				String email = myRs.getString("email");
				Character role =  myRs.getString("role").charAt(0);			
				//String pwd = myRs.getString("password");
				u = new Users(id,username,email,role);	
                        }
                        return u;
		}
		finally {
		//	db.close();
		}
	}	
	
	private String getEncrpytedPassword(int id) throws Exception {
		String encryptedPassword = null;
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
                String sql = "select password from users where id=?";
		
		try {
			myStmt = db.prepareStatement(sql);
                        myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				encryptedPassword = myRs.getString("password");
			}
			else {
				throw new Exception("User id not found: " + id);
			}

			return encryptedPassword;		
		}
		finally {
			db.close();
		}		
	}
       
        
    
}
