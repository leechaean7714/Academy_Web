package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.db.JDBCTemplate;
import com.cal.dto.CalDto;

public class CalDao extends JDBCTemplate {
	
	

	public int insertCalBoard(CalDto dto) {

		Connection con = getConnection();

		PreparedStatement pstm = null;
		int res = 0;

		String sql = "INSERT INTO CALBOARD VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());

			System.out.println("3.쿼리 준비");
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;

	}

	public List<CalDto> selectList(String id, String yyyyMMdd) {// yyyyMMdd 해당 일 전부가 필요해서(시간 분이 필요하지 않다.)
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT * FROM CALBOARD WHERE ID=? AND SUBSTR(MDATE,1,8) =?";
		CalDto dto = null;
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.쿼리 준비");
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);

			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");

			while (rs.next()) {

				dto = new CalDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getDate(6));

			}
			list.add(dto);

		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return list;
	}

	public List<CalDto> getCalViewList(String id, String yyyyMM) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<>();
		CalDto dto = null;

		String sql = " SELECT * FROM( "
				+ "   SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE))RN, "
				+ "   SEQ,ID,TITLE,CONTENT,MDATE,REGDATE" + "   FROM CALBOARD"
				+ "   WHERE ID = ? AND SUBSTR(MDATE,1,6)=? " + " ) " + "WHERE RN BETWEEN 1 AND 3 ";

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.쿼리 준비");
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);

			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");
			System.out.println("");
			while (rs.next()) {
				dto = new CalDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getDate(7));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return list;
	}

	public int getCalCount(String id, String yyyyMMdd) {// 어떤 년도 어떤 월 ㅡ어떤 일에 글 몇개를 썼냐.

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "SELECT COUNT(*) FROM CALBOARD WHERE ID=? AND SUBSTR(MDATE,1,8)=? ";

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.쿼리 준비");
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);

			rs = pstm.executeQuery();
			System.out.println("4.");
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.DB종료");
		}

		return res;
	}

}
