package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectApp {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			
			String query ="";
			query += " Select book_id, " ;
			query += " 				 title, ";
			query += " 				 pubs, ";
			query += " 				 pub_date, ";
			query += " 				 author_id ";
			query += " from books ";
			
			ps = conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int bookId=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String pubsDate=rs.getString("pub_date");
				int authorId=rs.getInt("author_id");
				
				System.out.println(bookId+", "+title+", "+pubs+", "+pubsDate+", "+authorId);
			}
					
			
		} catch(ClassNotFoundException e) {
			System.out.println("driver loading error" +e);
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("error: "+e);
			}
		}
	}
}
