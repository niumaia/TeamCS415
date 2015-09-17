package librarysystem;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class dbConnect {
	
	private Connection myConn;
        private Connection myConnStud;
	
	public dbConnect(String type) throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("project.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String db = props.getProperty("dbname");
                String stud = props.getProperty("dbstud");
		String url = props.getProperty("dburl");
		String conn = url + db;
		
		// connect to database
		//myConn = (Connection) DriverManager.getConnection(conn, user, password);
               // System.out.println("DB connection successful to: " + db);
                if(type.equals("Staff"))
                {
                connectStaff(conn,user,password);
                }else {
                    connectStudent(url + stud,user,password);               
                }

	}
        
        public void connectStudent(String conn,String user,String p) throws Exception 
        {
            myConnStud = (Connection) DriverManager.getConnection(conn, user, p);
        }
        
        public void connectStaff(String conn,String user,String p) throws Exception 
        {
            myConn = (Connection) DriverManager.getConnection(conn, user, p);
        }
        
        public Connection getStudentConnector()
	{
		return myConnStud;
	}
	
	public Connection getStaffConnector()
	{
		return myConn;
	}
	
}
