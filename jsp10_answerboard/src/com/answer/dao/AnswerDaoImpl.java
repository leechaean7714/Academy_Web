 package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;



import static com.answer.db.JDBCTemplate.*;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {

		Connection con = getConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.쿼리준비");
			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");
			while (rs.next()) {
				AnswerDto dto = new AnswerDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), 
						rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));

				list.add(dto);

			}
		} catch (SQLException e) {
			System.out.println("[error]:3,4");
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return list;
	}

	@Override

	public AnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		AnswerDto dto = null;

		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3.쿼리준비");

			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");
			while (rs.next()) {

				dto = new AnswerDto();

				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));

			}
		} catch (SQLException e) {
			System.out.println("5.db종료");
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstm);
			close(con);
		}

		return dto;

	}

	@Override
	public int insert(AnswerDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			System.out.println("3.쿼리준비");

			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

	@Override
	public int update(AnswerDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			System.out.println("3.query준비");

			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

	@Override
	public int delete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3.쿼리준비");
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

	public int answerUpdate(int parentboardno) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			//pstm = con.prepareStatement(UPDATE_SQL);
			pstm= con.prepareStatement(UPDATE_RE_SQL);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
			System.out.println("3.쿼리준비");
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);

			}else{
				rollback(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;

		int res = 0;

		try {
			pstm = con.prepareStatement(INSERT_RE_SQL);

			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			System.out.println("3.쿼리준비");

			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if (res > 0) {
				commit(con);
			}else {
				rollback(con);
			}

		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}

		return res;
	}

}
