package NesneyeYonelikProje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Veritabani {
	static String url ="jdbc:postgresql://localhost:5432/rentacar";
	static Connection conn= null;
	
	static Connection baglan() {
		try {
			conn =DriverManager.getConnection(url,"postgres", "1234");
			System.out.println("Bağlantı gerceklesti!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	static ResultSet listele(String sorgu) {
		try {
			Statement st= conn.createStatement();
			ResultSet rs= st.executeQuery(sorgu);
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void main(String[]args) {
		
		Veritabani.baglan();
		
		
		
	}
	
}

