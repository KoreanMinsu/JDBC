package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectApp {
	public static void main(String[] args) {
		
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "webdb","1234");
			
			String query="";
			query += " select b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs, ";
			query += " 			b.pub_date, ";
			query += " 			b.author_id, ";
			query += "			au.author_name, ";
			query += "			au.author_desc ";
			query += " from books b, authors au ";
			query += " where b.author_id=au.author_id ";
			
			ps =conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bookId=rs.getInt("book_id");
				String title = rs.getString("title");
		    	String pubs = rs.getString("pubs");
		    	String pubDate = rs.getString("pub_date");
		    	int authorId = rs.getInt("author_id");
		    	String authorName = rs.getString("author_name");
		    	String authorDesc = rs.getString("author_desc");
		    	
		    	System.out.println(bookId+ ", " + title + ", " + pubs + ", " + pubDate + ", " + authorId + ", " + authorName + ", " + authorDesc);
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("driver not loaded : "+e);
		}catch(SQLException e) {
			System.out.println("error:"+e);
		}finally {
			
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
			}catch(SQLException e) {
				System.out.println("error"+e);
			}
		}
		
	}
}
