import java.sql.*;
import javax.swing.*;

public class sqLiteConnection {
	
	Connection conn=null;
	
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn= DriverManager.getConnection("jdbc:sqlite:D:\\SQLiteStudio\\KomisSamochodowy");
			JOptionPane.showMessageDialog(null, "Nawiązano połączenie");
			return conn;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
