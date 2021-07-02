package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdateApp {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");

			String query = "";
			query += " update book ";
			query += " set title = ?, pubs = ? where book_id = ? ";

			ps = conn.prepareStatement(query);
			ps.setString(1, "민수킴");
			ps.setString(2, "안녕하세요");
			ps.setString(3, "13");
			int count = ps.executeUpdate();

			System.out.println(count + "건이 수정되엇다.");

		} catch (ClassNotFoundException e) {
			System.out.println("driver not loaded: " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	}
}
