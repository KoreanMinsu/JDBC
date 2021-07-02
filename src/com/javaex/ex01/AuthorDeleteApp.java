package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDeleteApp {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			
			String query = "";
			query += " delete from books ";
			query += " where author_id = ? ";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, "2"); //? 중 1번째 - 순서 중요함
			
			int count = ps.executeUpdate();
		
			System.out.println(count + "건이 삭제됨");
			
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		} catch(SQLException e) {
			System.out.println(e);
		} finally {
			
			try {
				if(ps!=null) {
					ps.close();
				} if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println(e);
			}
			
		}
	}
}
