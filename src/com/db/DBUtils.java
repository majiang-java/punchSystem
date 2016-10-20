package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtils {
	private static DBUtils instance;
	
	public static DBUtils getInstance(){
		if(instance == null){
			instance = new DBUtils();
		}
		return instance;
	}
	
	static {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();

		}
			
	}
	public Connection getConnection() throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
		return c;
	}
	
	public static void close(Connection conn,PreparedStatement ptmt,ResultSet rs){
		try{
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(ptmt != null){
				ptmt.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(rs != null){
				rs.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
