package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdateApp {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");

			String query = "";
			query += " update author ";
			query += " set author_name = ?, author_desc = ? , where author_id = ? ";

			ps = conn.prepareStatement(query);
			ps.setString(1, "민슈");
			ps.setString(2, "IT");
			ps.setString(3, "9");
			int count = ps.executeUpdate();

			System.out.println(count + "건이 수정되었다.");

		} catch (ClassNotFoundException e) {
			System.out.println("driver not loaded:" + e);
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
				System.out.println("error: " + e);
			}
		}
	}
}
