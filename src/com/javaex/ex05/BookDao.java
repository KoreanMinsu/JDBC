package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
//field
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";

//method-generic
	//Driver, connection
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속완료");
		} catch (ClassNotFoundException e) {
			System.out.println("driver load error" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}
	//resource clearing
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//bookInsert
	public int BookInsert(BookVo bookVo) {
		int count = -1;
		this.getConnection();
		try {
			String query = "";
			query += " insert into books ";
			query += " values(seq_book_id.nextval, ?, ?, ? )";

			ps = conn.prepareStatement(query);
			ps.setInt(1, bookVo.getBookId());
			ps.setString(2, bookVo.getTitle());
			ps.setString(3, bookVo.getPubs());
			ps.setString(4, bookVo.getPubDate());

			count = ps.executeUpdate();

			System.out.println(count + "건이 등록됨");

		} catch (SQLException e) {
			e.getStackTrace();
		}
		this.close();
		return count;
	}
	
	//bookDelete
	public int BookDelete(int bookId) {
		int count = -1;
		this.getConnection();
		
		try {
			String query = "";
			query += " delete from books ";
			query += " where book_id = ? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			
			count = ps.executeUpdate();
			
			System.out.println(count+ "건 삭제했어요");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return count;
	}
	
	//BookUpdate
	public int BookUpdate(BookVo bookVo) {
		int count = -1;
		this.getConnection();
		
		try {
			String query ="";
			query += " update books ";
			query += " set   title = ?, ";
			query += "       pubs = ?, ";
			query += "       pub_date = ?, ";
			query += " where book_id = ? ";
			
			ps=conn.prepareStatement(query);
			ps.setString(1, bookVo.getTitle());
			ps.setString(2, bookVo.getPubs());
			ps.setString(3, bookVo.getPubDate());
			ps.setInt(4, bookVo.getBookId());
			
			count=ps.executeUpdate();
			System.out.println(count+"건 수정했다");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.close();
		return count;
	}
	
	//BookList
	public List<BookVo> getBookList() {

		List<BookVo> bookList = new ArrayList<BookVo>();

		this.getConnection();

		try {
			String query = "";
			query += " select b.book_id, ";
			query += " 		   b.title, ";
			query += "          b.pubs, ";
			query += "          b.pub_date; ";
			query += "          au.author_id ";
			query += " from books b, authors au ";
			query += " where b.author_id = au.author_id ";

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("b.bookId");
				String bookTitle = rs.getString("b.bookTitle");
				String bookPubs = rs.getString("b.bookPubs");
				String bookPubDate = rs.getString("b.bookPubDate");
				int authorId = rs.getInt("au.authorId");

				BookVo bookVo = new BookVo(bookId, bookTitle, bookPubs, bookPubDate, authorId);

				bookList.add(bookVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();

		return bookList;
	}
	
	//select BookList
	public List<BookVo> getBookList(String search){
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		this.getConnection();
		try {
			String query = "";
			query += " select b.book_id, ";
			query += " 		   b.title, ";
			query += "          b.pubs, ";
			query += "          b.pub_date; ";
			query += "          au.author_id ";
			query += " from books b, authors au ";
			query += " where b.author_id = au.author_id ";
			query += " or b.book_id like '%" + search + "%' ";
			query += " or b.book_id like '%" + search + "%' ";
			query += " or b.book_id like '%" + search + "%' ";
			
			ps = conn.prepareStatement(query);
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("b.bookId");
				String bookTitle = rs.getString("b.bookTitle");
				String bookPubs = rs.getString("b.bookPubs");
				String bookPubDate = rs.getString("b.bookPubDate");
				int authorId = rs.getInt("au.authorId");

				BookVo bookVo = new BookVo(bookId, bookTitle, bookPubs, bookPubDate, authorId);

				bookList.add(bookVo);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return bookList;
	}

}
