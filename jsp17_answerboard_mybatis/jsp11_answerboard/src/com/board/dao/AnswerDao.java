package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.db.JDBCTemplate;
import com.board.dto.AnswerDto;
import com.sun.imageio.stream.StreamCloser.CloseAction;

public class AnswerDao extends JDBCTemplate{

	public List<AnswerDto> selectList(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		String sql = " SELECT * FROM ANSWERBOARD_D "
				   + " ORDER BY GROUPNO DESC, GROUPSEQ ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.query 준비 : " + sql);
			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");
			while(rs.next()) {
				AnswerDto dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				dto.setFlagdel(rs.getString(9));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs,pstm,con);
			System.out.println("5.db 종료");
		}
		
		return list;
	}
	
	public AnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM ANSWERBOARD_D "
				   + " WHERE BOARDNO = ? ";
		AnswerDto dto = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			System.out.println("3.query 준비 : " + sql);
			rs = pstm.executeQuery();
			System.out.println("4.실행 및 리턴");
			while(rs.next()) {
				dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				dto.setFlagdel(rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		} finally {
			close(rs,pstm,con);
			System.out.println("5.db 종료");
		}
		
		return dto;
	}
	
	public int insert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO ANSWERBOARD_D "
				   + " VALUES(BOARDNOSEQ_D.NEXTVAL,GROUPNOSEQ_D.NEXTVAL, "
				   + " 1,0,?,?,?,SYSDATE,'N') ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			System.out.println("3.query 준비 : " + sql);
		
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5.db 종료");
		}
		
		return res;
	}

	public int update(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD_D"
				   + " SET TITLE = ?, CONTENT = ? "
				   + " WHERE BOARDNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			System.out.println("3.query 준비 : " + sql);
		
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5.db 종료");
		}
		
		return res;
	}
	
	public int delete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD_D SET FLAGDEL = 'Y' WHERE BOARDNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			System.out.println("3.query 준비 : " + sql);
		
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
			System.out.println("5.db 종료");
		}
		
		return res;
	}
	
	public int answerUpdate(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD_D SET GROUPSEQ = GROUPSEQ + 1 WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = ? ) AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = ? )";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm,con);
		
		}
		
		return res;
	}
	
	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO ANSWERBOARD_D VALUES( BOARDNOSEQ_D.NEXTVAL, (SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = ? ), (SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = ? ) + 1, (SELECT TITLETAB FROM ANSWERBOARD_D WHERE BOARDNO = ?) + 1, ?, ?, ?, SYSDATE, 'N' )";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	public int selectOneNo(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql=" UPDATE ANSWERBOARD_D SET HITNO = HITNO + 1 WHERE BOARDNO= ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			System.out.println("3.쿼리준비"+sql);
			res=pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if(res>0) {
				commit(con);
			}
					
		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		}finally {
			
		close(pstm,con);
	
		}
		return res;
	}
	
}













