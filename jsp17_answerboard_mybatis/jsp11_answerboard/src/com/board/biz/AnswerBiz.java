package com.board.biz;

import java.util.List;

import com.board.dao.AnswerDao;
import com.board.dto.AnswerDto;

public class AnswerBiz {
	
	private AnswerDao dao = new AnswerDao();

	public List<AnswerDto> selectList(){
		return dao.selectList();
	}
	public AnswerDto selectOne(int boardno) {
		
		return dao.selectOne(boardno);
	}
	
	public int insert(AnswerDto dto) {
		return dao.insert(dto);
	}

	public int update(AnswerDto dto) {
		return dao.update(dto);
	}
	
	public int delete(int boardno) {
		return dao.delete(boardno);
	}
	
	public int insert_answer(AnswerDto dto) {
		int updateRes = dao.answerUpdate(dto.getBoardno());
		int insertRes = dao.answerInsert(dto);
		System.out.println(updateRes);
		System.out.println(insertRes);
		
		return updateRes + insertRes;
	}
	
	public int selectOneNo(int boardno) {
		
		return dao.selectOneNo(boardno);
	}
}







