package NesneyeYonelikProje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase {
	static String url ="jdbc:postgresql://localhost:5432/rentacar";

	static Connection conn= null;
	static void baglan() {
		try {
			conn =DriverManager.getConnection(url,"postgres", "1234");
			System.out.println("Bağlantı gerceklesti!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	static void vericek() {
		try {
			conn =DriverManager.getConnection(url,"postgres", "1234");
			String query="select *from users";
			
			Statement st= conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			System.out.println("vericeke girdi");
			while(rs.next()) {
				
				System.out.println(rs.getString("kullanici_ad"));
				System.out.println(rs.getString("kullanicisifre"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		
		UserDatabase.baglan();
		UserDatabase.vericek();
		
	}
}
