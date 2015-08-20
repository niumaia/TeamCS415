package librarysystem;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class dbConnect {
	
	private Connection myConn;
	
	public dbConnect() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("project.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String db = props.getProperty("dbname");
		String url = props.getProperty("dburl");
		String conn = url + db;
		
		// connect to database
		myConn = (Connection) DriverManager.getConnection(conn, user, password);

	}
	
	public Connection getConnector()
	{
		return myConn;
	}
	
}
