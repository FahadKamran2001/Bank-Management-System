package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	public Connection databaseLink;
	
	public Connection getConnection() {	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			databaseLink = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-F6HP864:1521:fahad","system","fahad");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return databaseLink;
	}
		
}
