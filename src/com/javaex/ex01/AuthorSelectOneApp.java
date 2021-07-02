package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelectOneApp {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OraccleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb","1234");
			
			String query = "";
			query += " select author_id, ";
			query += " 			        author_name, ";
			query += "					author_desc, ";
			query += " from authors ";
			query += " where author_id = ? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, 5);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				int authorId=rs.getInt("author_id");
				String authorName=rs.getString("author_name");
				String authorDesc=rs.getString("author_desc");
				
				System.out.println(authorId + ","+authorName + ","+authorDesc);
			}
					
					
		} catch(ClassNotFoundException e) {
			System.out.println("Driver loading error:"+e);
		} catch(SQLException e) {
			System.out.println("error"+e);
		} finally {
			
			try {
				if(rs !=null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				System.out.println("error"+e);
			}
		}
	
	}
}
