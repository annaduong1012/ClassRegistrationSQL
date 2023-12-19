package course.registration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/class_registration?serverTimezone=Australia/Melbourne";
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/class_registration?serverTimezone=Australia/Melbourne", 
					"be5_newuser",
					"Be5M95ql*");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
