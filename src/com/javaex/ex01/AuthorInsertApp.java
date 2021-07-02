package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsertApp {
	public static void main(String[] args) {
		// 0. import java.sql.*;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " Insert into authors ";
			query += " values ( seq_author_id.nextval, ?, ? ) ";

			System.out.println(query);

			ps = conn.prepareStatement(query);
			ps.setString(1, "민수");
			ps.setString(2, "JDBC test");

			int count = ps.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc Driver loading error" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
			// 5. 자원정리
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error :" + e);
			}
		}
	}
}
