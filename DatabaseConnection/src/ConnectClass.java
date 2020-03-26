import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectClass {

	//DB Connection Variable
	
	static Connection con = null;
	static String databaseName = "studentdatabase";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	static String username = "root";
	static String password = "1234";
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("INSERT INTO table1 (studentID,name) VALUES (?,?)");
		ps.setString(1, "2");
		ps.setString(2, "Harshubh");
		int status = ps.executeUpdate();
		
		if(status != 0)
		{
			System.out.println("Database was connected\nRecord was inserted");
			
		}
	}

}
