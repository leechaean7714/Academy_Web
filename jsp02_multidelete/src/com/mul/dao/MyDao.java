package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mul.db.JDBCTemplate;
import com.mul.dto.MyDto;
import com.sun.crypto.provider.RSACipher;

public class MyDao extends JDBCTemplate {

	public int multiDelete(String[] seq) {
		for (int i = 0; i < seq.length; i++) {
			System.out.println("seq->" + seq[i]);
		}
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;

		String sql = " DELETE FROM MDBOARDPRAC WHERE SEQ = ? ";

		try {
			pstm = con.prepareStatement(sql);
			for (int i = 0; i < seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				System.out.println("delete no:" + seq[i]);
			}
			System.out.println("3.쿼리 준비");

			cnt = pstm.executeBatch();

			System.out.println("4.실행 및 리턴");

			for (int i = 0; i < cnt.length; i++) {
				System.out.println("cnt[i]->" + cnt[i]);
				if (cnt[i] == -2) {
					res++;
				}
			}
			if (res == seq.length) {
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

	public int delete(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARDPRAC WHERE SEQ=? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3.쿼리 준비" + sql);

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

		}

		return res;
	}

	public List<MyDto> selectList() {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MDBOARDPRAC ORDER BY SEQ DESC";
		List<MyDto> list = new ArrayList<MyDto>();

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.쿼리준비");
			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");

			while (rs.next()) {
				MyDto dto = new MyDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));

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

	public MyDto selectOne(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARDPRAC WHERE SEQ = ? ";
		MyDto dto = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3.쿼리 준비" + sql);
			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");

			while (rs.next()) {
				dto = new MyDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));

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

		return dto;
	}

	public int insert(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = " INSERT INTO MDBOARDPRAC VALUES(MDBOARDPRACSEQ.NEXTVAL, ?,?,?,SYSDATE)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3.쿼리 준비");

			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);

			}

		} catch (SQLException e) {
			System.out.println("error:3.4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");

		}

		return res;
	}

	public int update(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE MDBOARDPRAC SET TITLE=?, CONTENT =? WHERE SEQ =? ";

		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.쿼리 준비" + sql);

			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());

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
		}

		return res;
	}

}
