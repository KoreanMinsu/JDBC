/*메소드파라미러를 Vo사용*/
package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";

	// 메소드-일반
	// Driver connection 메소두 만들기
	private void getConnection() {
		try {
			Class.forName(driver);
			System.out.println("driver loaded");

			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속완료");

		} catch (ClassNotFoundException e) {
			System.out.println("driver load error" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 작가 삭제하기
	public int authorDelete(int authorId) {
		int count = -1;

		// 0. import java.sql.*;
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from authors ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건삭제");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 자원정리
		this.close();

		return count;
	}

	// 작가 수정하기
	public int authorUpdate(AuthorVo authorVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update authors ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return count;
	}

	// 작가 등록하기
	public int authorInsert(AuthorVo authorVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into authors ";
			query += " values(seq_author_id.nextval, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count; // 성공갯수 리턴
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {
		// DB값을 가져와서 ArrayList로 전달

		// 리스트 생성
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  author_id, ";
			query += "         author_name, ";
			query += "         author_desc ";
			query += " from authors ";
			query += " order by author_id asc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);

				authorList.add(authorVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return authorList;

	}
}
