package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertApp {
	public static void main(String[] args) {
	//import java.sql	
		Connection conn = null;
		PreparedStatement ps = null;
	
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			
			String query = "";
			query += " Insert into books  ";
			query += " values ( seq_book_id.nextval, ?, ?, ?, ?) ";
			
			System.out.println(query);
			
			ps = conn.prepareStatement(query);
			ps.setString(1, "26년");
			ps.setString(2, "재미주의");
			ps.setString(3, "2012-02-04");
			ps.setString(4, "5");
			
			int count = ps.executeUpdate();
			
			System.out.println(count + "건이 저장됨.");
		}catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}catch(SQLException e) {
			System.out.println("error:" +e);
		} finally {
			try {
				
				if(ps!= null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("error:" +e);
			}
		}
		
				
	}
}
