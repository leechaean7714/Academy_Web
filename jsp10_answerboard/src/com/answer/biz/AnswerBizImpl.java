package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {


	private AnswerDao dao = new AnswerDaoImpl();
	
	
	public List<AnswerDto> selectList() {
		
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		
		return dao.selectOne(boardno);
	}

	@Override
	public int insert(AnswerDto dto) {
		
		return dao.insert(dto)
				;
	}

	@Override
	public int update(AnswerDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		
		return dao.delete(boardno);
	}
	
	
	
	
	
	
	public int answerProc(AnswerDto dto) {
		
		int updateRes=dao.answerUpdate(dto.getBoardno());
		int insertRes=dao.answerInsert(dto);
		
		//System.out.println(updateRes);
		//System.out.println(insertRes);
		
		return updateRes+insertRes;
	}

}
