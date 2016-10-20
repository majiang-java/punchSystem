package com.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DBUtils;

public class PersonServices {
	
	private static PersonServices personServices;
	public static PersonServices getInstance(){
		if(personServices == null){
			personServices = new PersonServices();
		}
		return personServices;
	}
	
	public List<Map<String,String>> getPersonList() throws SQLException {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			String sql = "select * from persons";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("CardNum", rs.getString("CardNum"));
				hm.put("Name", rs.getString("Name"));
				hm.put("CardLog", rs.getString("CardLog"));
				hm.put("LastModifyTime", rs.getString("LastModifyTime"));
				list.add(hm);
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return list;
		
	}
	
	public int insertPerson(String CardNum,String Name,String CardLog) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int i;
		try{
			conn = DBUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			conn.commit();
			String sql = "insert into persons (CARDNUM, NAME, CARDLOG, LASTMODIFYTIME)" +
					"values(?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CardNum);
			pstmt.setString(2, Name);
			pstmt.setString(3, CardLog);
			i = pstmt.executeUpdate();
			conn.setAutoCommit(true);
			conn.commit();
		}catch(Exception e){
			conn.setAutoCommit(true);
			conn.rollback();
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		return i;
	}

	public String getCardLog(String CARDNUM) throws SQLException{
		String CARDLOG = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			String sql = "select CARDLOG from persons where CARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CARDNUM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				CARDLOG = rs.getString("CARDLOG");
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return CARDLOG;
	}

	public Map<String,String> getPersonMsg(String cardNum) throws SQLException {
		Map<String,String> hm = new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			String sql = "select * from persons where CARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNum);
			rs = pstmt.executeQuery();
			if(rs.next()){
			
				hm.put("CardNum", rs.getString("CardNum"));
				hm.put("Name", rs.getString("Name"));
				hm.put("CardLog", rs.getString("CardLog"));
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return hm;
	}

	public int delete(String cardNum) throws SQLException {
		int flag = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			String sql = "delete from persons where CARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNum);
			flag = pstmt.executeUpdate();
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		return flag;
	}

	public void logCard(String cardNum,int logCard) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql = "update persons set CardLOG =?,LASTMODIFYTIME = sysdate where CARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(logCard));
			pstmt.setString(2, cardNum);
			pstmt.execute();
			
			conn.commit();
			conn.setAutoCommit(true);
			insertLog(cardNum,logCard);
		}catch(Exception e){
			conn.setAutoCommit(true);
			conn.rollback();
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		
	}

	public String getTitle(String title) throws SQLException {
		String titleMsg = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtils.getInstance().getConnection();
			String sql = "select value from comcde where cde = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if(rs.next()){
				titleMsg = rs.getString("value");
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return titleMsg;
		
	}

	public int setTitle(String titleMsg) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int returnFlag;
		try{
			conn = DBUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql = "update comcde set value =? where cde = 'title'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, titleMsg);
			returnFlag = pstmt.executeUpdate();
			conn.setAutoCommit(true);
			conn.commit();
		}catch(Exception e){
			conn.setAutoCommit(true);
			conn.rollback();
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		return returnFlag;
		
	}

	public int update(String cardNum, String name, String cardLog) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int returnFlag;
		try{
			conn = DBUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql = "update persons set cardNum =?,name=?,cardLog=?,LASTMODIFYTIME = sysdate where CARDNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNum);
			pstmt.setString(2, name);
			pstmt.setString(3, cardLog);
			pstmt.setString(4, cardNum);
			returnFlag  = pstmt.executeUpdate();
			conn.setAutoCommit(true);
			conn.commit();
		}catch(Exception e){
			conn.setAutoCommit(true);
			conn.rollback();
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		return returnFlag;
		
	}
	
	public int insertLog(String CardNum,int CardLog) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int i;
		try{
			conn = DBUtils.getInstance().getConnection();
			conn.setAutoCommit(false);
			conn.commit();
			String sql = "insert into log (CARDNUM, NAME, CARDLOG, LASTMODIFYDATE)" +
					"values(?,(select Name from persons t where t.cardNum = ?),?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CardNum);
			pstmt.setString(2, CardNum);
			pstmt.setString(3, String.valueOf(CardLog));
			
			i = pstmt.executeUpdate();
			conn.setAutoCommit(true);
			conn.commit();
		}catch(Exception e){
			conn.setAutoCommit(true);
			conn.rollback();
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, null);
		}
		return i;
	}
	
	
	

	
	
	
}
