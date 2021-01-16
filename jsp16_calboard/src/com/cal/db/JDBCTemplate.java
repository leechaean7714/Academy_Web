package com.cal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
	
	public static Connection  getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.드라이버 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("error:1");
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@192.168.235.1:1521:xe";
		String user="ca";
		String password="ca";
		
		Connection con = null;
		try {
			con=DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2계정 연결");
		} catch (SQLException e) {
			System.out.println("error:2");
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	public static void close(PreparedStatement pstm) {
		
		try {
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(ResultSet rs ) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
