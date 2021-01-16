package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.mul.db.JDBCTemplate.*;
import com.mul.dto.MyDto;

public class MyDao01 {

	public List<MyDto> selectList() {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MDBOARDPRAC ORDER BY SEQ DESC ";

		List<MyDto> list = new ArrayList<MyDto>();

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("쿼리 준비");

			rs = pstm.executeQuery();
			System.out.println("실행 및 리턴");

			while (rs.next()) {
				MyDto dto = new MyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
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

	public MyDto selectOne(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MYBOARD WHERE SEQ =? ";

		MyDto dto = new MyDto();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("쿼리준비");
			rs = pstm.executeQuery();
			System.out.println("실행 및 리턴");
			while (rs.next()) {
				dto = new MyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));

			}
		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
		}

		return dto;

	}

	public int insert(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO MYBOARD VALUES(SEQSEQ.NEXTVAL,?,?,?,SYSDATE)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());

			System.out.println("쿼리준비" + sql);
            res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}

		return res;
	}

	public int update(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE MYBOARD SET TITLE=?,CONTENT=? WHERE SEQ=? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());

			System.out.println("쿼리 준비" + sql);
			res=pstm.executeUpdate();
			if (res > 0) {
				commit(con);

			}
		} catch (SQLException e) {
		System.out.println("err0r:3,4");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}

		return res;
	}
	
	public int delete(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql="DELETE FROM MYBOARD WHERE SEQ =? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("쿼리준비"+sql);
			
			res=pstm.executeUpdate();
			if(res>0) {
				commit(con);
				
			}
		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		}finally {
			close(pstm);close(con);
		}
		
		
		return res;
	}

}
