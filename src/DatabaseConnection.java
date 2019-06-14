import java.sql.*;
public class DatabaseConnection {
static Connection database() throws Exception {
		String driverClass="com.mysql.jdbc.Driver";
		String databaseUrl="jdbc:mysql://localhost:3306/Bank";
		String username="root";
		String password="";
		Class.forName(driverClass);
		Connection cn =DriverManager.getConnection(databaseUrl,username,password);
		return cn;
	}

}
