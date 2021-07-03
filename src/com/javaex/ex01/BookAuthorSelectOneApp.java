package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectOneApp {
	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url, "webdb", "1234");
			
			String query="";
			query += " select b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs, ";
			query += " 			b.pub_date, ";
			query += " 			b.author_id, ";
			query += "			au.author_name, ";
			query += "			au.author_desc ";
			query += " from books b, authors au, ";
			query += " where b.author_id=au.author_id ";
			query += " and book_id = ? ";

			
			ps =conn.prepareStatement(query);
			ps.setInt(1, 5);
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
			
		} catch(ClassNotFoundException e) {
			System.out.println("driver is not loaded:"+ e);
		}catch(SQLException e) {
			System.out.println("error:"+e);
		}
	}
}
