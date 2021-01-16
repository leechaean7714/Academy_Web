package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MVCDao;
import com.mvc.dao.MVCDaoImple;
import com.mvc.dto.MVCDto;

public class MVCBizImple implements MVCBiz {

	
	private MVCDao dao = new MVCDaoImple();
	
	@Override
	public List<MVCDto> selectList() {
		
		return dao.selectList();
	}

	@Override
	public MVCDto selectOne(int seq) {
		
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MVCDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(MVCDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		
		return dao.delete(seq);
	}

	@Override
	public boolean multiDelete(String[] seqs) {
		
		return dao.multiDelete(seqs);
	}

}
