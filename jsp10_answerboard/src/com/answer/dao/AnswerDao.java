package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	
	String SELECT_LIST_SQL=" SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSEQ ";
	String SELECT_ONE_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, TITLE, CONTENT,WRITER,REGDATE "
			            + "FROM ANSWERBOARD WHERE BOARDNO =? ";
	
	
	
	String INSERT_SQL=" INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL,"
			        + "1,0,?,?,?,SYSDATE) ";
	
	
	String INSERT_RE_SQL="INSERT INTO ANSWERBOARD VALUES("
			             + " BOARDNOSEQ.NEXTVAL,"
			             + "(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =?),"
			             + "(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =?)+1,"
			             + "(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =?)+1,"
			             + " ?,?,?,SYSDATE) ";
	
	
	String UPDATE_SQL="	UPDATE ANSWERBOARD SET TITLE = ? , CONTENT = ? WHERE BOARDNO = ? ";
	
	String UPDATE_RE_SQL=" UPDATE ANSWERBOARD SET GROUPSEQ = GROUPSEQ+1"
	                   + " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =?)"
	                   + " AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =?)";
	
	
	
	String DELETE_SQL="DELETE FROM ANSWERBOARD WHERE BOARDNO=? ";
	
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insert(AnswerDto dto);
	public int update(AnswerDto dto);
	public int delete(int boardno);
	
	public int answerUpdate(int parentboardno);
	public int answerInsert(AnswerDto dto);
}
