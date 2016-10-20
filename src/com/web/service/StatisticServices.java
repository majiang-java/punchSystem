package com.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.DBUtils;

public class StatisticServices {

	private static StatisticServices statisticServices;
	public static StatisticServices getInstance(){
		if(statisticServices == null){
			statisticServices = new StatisticServices();
		}
		return statisticServices;
	}
	
	
	public List<Map<String, String>> getStatisticList(String cardNum,String name,Date highDate,Date lowDate ) throws SQLException{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		try{
			List<Object> param = new ArrayList<Object>();
			conn = DBUtils.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select  g.cardNum,g.name,g.cardLog,g.lastModifyDate from log g where 1=1");
			if(cardNum != null&&!"".equals(cardNum)){
				sb.append(" and cardNum =?");
				param.add(cardNum);
			}
			if(name != null&&!"".equals(name)){
				sb.append(" and name =?");
				param.add(name);
			}
			if(highDate != null&&!"".equals(highDate)){
				sb.append(" and lastModifyDate <=?");
				param.add(new java.sql.Date(highDate.getTime()));
			}
			if(lowDate != null&&!"".equals(lowDate)){
				sb.append(" and lastModifyDate >=?");
				param.add(new java.sql.Date(lowDate.getTime()));
			}
			pstmt = conn.prepareStatement(sb.toString());
			if(param.size()!=0){
				for (int i = 0; i < param.size(); i++) {
					pstmt.setObject(1+i, param.get(i));
				}
			}
			System.out.println(param);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("cardNum", rs.getString("cardNum"));
				hm.put("name", rs.getString("name"));
				hm.put("cardLog", rs.getString("cardLog"));
				hm.put("lastModifyDate", rs.getString("lastModifyDate"));
				list.add(hm);
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public String getCount(String cardNum,String name,Date highDate,Date lowDate ) throws SQLException{
		String count = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		try{
			List<Object> param = new ArrayList<Object>();
			conn = DBUtils.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*)  from log g where 1=1");
			if(cardNum != null&&!"".equals(cardNum)){
				sb.append(" and cardNum =?");
				param.add(cardNum);
			}
			if(name != null&&!"".equals(name)){
				sb.append(" and name =?");
				param.add(name);
			}
			if(highDate != null&&!"".equals(highDate)){
				sb.append(" and lastModifyDate <=?");
				param.add(new java.sql.Date(highDate.getTime()));
			}
			if(lowDate != null&&!"".equals(lowDate)){
				sb.append(" and lastModifyDate >=?");
				param.add(new java.sql.Date(lowDate.getTime()));
			}
			pstmt = conn.prepareStatement(sb.toString());
			if(param.size()!=0){
				for (int i = 0; i < param.size(); i++) {
					pstmt.setObject(i+1, param.get(i));
				}
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getString("C1");
			}
		}catch(Exception e){
			throw new SQLException(e);
		}finally{
			DBUtils.close(conn, pstmt, rs);
		}
		return count;
	}
	
	

	
	
}
