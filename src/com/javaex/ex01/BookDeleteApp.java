package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDeleteApp {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin@localhost:1421:xe";
			DriverManager.getConnection(url, "webdb", "1234");
			
			String query = ""; // 쿼리문 문자열로 ? 주의
			
			query += " delete from book ";
			query += " where book_id= ? ";
			
			ps = conn.prepareStatement(query); // 문자열-> 쿼리문
			ps.setString(1 ,"12"); // ? 중 1번째 순서중요
			
			int count = ps.executeUpdate();
			
			System.out.println(count + "건 삭제됨");
			
		} catch(ClassNotFoundException e){
			System.out.println("driver loading error"+ e);
		} catch(SQLException e) {
			System.out.println("oracle connection error"+e);
		} finally {
			
			try {
				if(ps!=null) {
				ps.close();
			} if(conn!=null) {
				conn.close();
			}
			
			} catch(SQLException e) {
				System.out.println("error :" + e);
			}
		}
		
	}
		
}
