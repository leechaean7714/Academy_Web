package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MyDto;
import static com.mvc.db.JDBCTemplate.*;

public class MyDaoImpl implements MyDao {

	@Override
	public List<MyDto> selectList() {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<MyDto> list = new ArrayList<MyDto>();

		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				MyDto dto = new MyDto(rs.getInt(1),
						              rs.getString(2),
						              rs.getString(3),
						              rs.getString(4),
						              rs.getDate(5));
				/*
				 * dto.setSeq(rs.getInt(1)); dto.setWriter(rs.getString(2));
				 * dto.setTitle(rs.getString(3)); dto.setContent(rs.getString(4));
				 * dto.setRegdate(rs.getDate(5));
				 */

				list.add(dto);

			}

		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return list;
	}

	@Override
	public MyDto selectOne(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		MyDto dto = null;

		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs = pstm.executeQuery();

			while (rs.next()) {
				dto = new MyDto();
				
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return dto;
	}

	@Override
	public int insert(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_SQL);

			pstm.setString(1, dto.getWriter());//쿼리를 넣는다.
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}

		return res;
	}

	@Override
	public int update(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());

			res = pstm.executeUpdate();
			if (res > 0) {

				commit(con);// db적용, 변경
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}

		return res;
	}

	@Override
	public int delete(int seq) {
	     Connection con = getConnection();
	     PreparedStatement pstm = null;
	     int res = 0;
	     
	     try {
			pstm=con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			
			res=pstm.executeUpdate();
			if(res>0) {
				
				commit(con);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			
		}
	    		 
		return res;
	}

}
