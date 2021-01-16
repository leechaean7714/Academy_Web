package com.mvc.dao;

import static com.mvc.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCDto;

public class MVCDaoImple implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		Connection con = getConnection();//
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<MVCDto> list = new ArrayList<MVCDto>();

		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);

			// 4
			/*
			 * .executeQuery(); 1. 수행결과로 ResultSet 객체의 값을 반환합니다. 2. SELECT 구문을 수행할 때 사용되는
			 * 함수입니다. executeQuery 함수를 사용하는 방법입니다. -> ResultSet 객체에 결과값을 담을 수 있습니다.
			 */

			rs = pstm.executeQuery();

			while (rs.next()) {
				MVCDto dto = new MVCDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				list.add(dto);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstm.close();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCDto dto = null;

		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);

			pstm.setInt(1, seq);// 첫번쨰 물을표에
			System.out.println("3");

			// 4
			/*
			 * .executeQuery(); 1. 수행결과로 ResultSet 객체의 값을 반환합니다. 2. SELECT 구문을 수행할 때 사용되는
			 * 함수입니다. executeQuery 함수를 사용하는 방법입니다. -> ResultSet 객체에 결과값을 담을 수 있습니다.
			 */
			rs = pstm.executeQuery();
			System.out.println("4");
			while (rs.next()) {
				dto = new MVCDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));

				/*
				 * dto.setSeq(rs.getInt(1)); 2:저장 1: 읽어서 dto.setWriter(rs.getString(2));
				 * dto.setTitle(rs.getString(3)); dto.setContent(rs.getString(4));
				 * dto.setRegdate(rs.getDate(5));
				 */

			}

		} catch (SQLException e) {
			System.out.println("[error]:3,4");
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return dto;
	}

	@Override
	public int insert(MVCDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_SQL);

			pstm.setString(1, dto.getWriter());
			// 1:
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3쿼리 준비" + INSERT_SQL);

			// 4

			/*
			 * .executeUpdate(); 1. 수행결과로 Int 타입의 값을 반환합니다. 2. SELECT 구문을 제외한 다른 구문을 수행할 때
			 * 사용되는 함수입니다. executeUpdate 함수를 사용하는 방법입니다. -> INSERT / DELETE / UPDATE 관련
			 * 구문에서는 반영된 레코드의 건수를 반환합니다. -> CREATE / DROP 관련 구문에서는 -1 을 반환합니다.
			 */
			res = pstm.executeUpdate();
			System.out.println("4,실행 및 리턴");

			if (res > 0) {
				commit(con);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
			System.out.println("db종료");
		}

		return res;
	}

	@Override
	public int update(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(UPDATE_SQL);

			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			// 4.
			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			System.out.println("");
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
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);

			res = pstm.executeUpdate();

			if (res > 0) {
				commit(con);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}

		return res;
	}

	@Override
	public boolean multiDelete(String[] seqs) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;

		try {
			pstm = con.prepareStatement(DELETE_SQL);
			for (int i = 0; i < seqs.length; i++) {

				pstm.setString(1, seqs[i]);
				pstm.addBatch();// 메모리에 한꺼번에
				System.out.println("삭제할 번호" + seqs[i]);
			}
			System.out.println("3쿼리 준비" + "DELETE_SQL");
			/* pstm.setInt(1, seq); */
			cnt = pstm.executeBatch();
			System.out.println("4.실행 및 리턴");

			for (int i = 0; i < cnt.length; i++) {

				if (cnt[i] == -2) {// 성공시 -2이다.
					res++;
				}
			}
			if (res == seqs.length) {
				commit(con);
			} else {
				rollback(con);
				res = 0;
			}

		} catch (SQLException e) {
			System.out.println("[error]:3,4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return (res == seqs.length) ? true : false;//

	}

}
