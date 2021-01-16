package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MVCDao;
import com.mvc.dao.MVCDaoImpl;
import com.mvc.dto.MVCDto;
import com.mvc.dto.PagingDto;

public class MVCBizImpl implements MVCBIz {

	private MVCDao dao = new MVCDaoImpl();

	@Override
	public List<MVCDto> selectList() {
		/*
		 * int page = dto.getPage(); int rows = dto.getRows();
		 * 
		 * int to = rows * (page - 1) + 1; int from = rows * page;
		 */
		return dao.selectList();
	}

	@Override
	public MVCDto selectOne(int seq) {

		return dao.selectOne(seq);
	}

	@Override
	public int insert(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(MVCDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}

	public int totalPage(int rows) {
		int totalpage = (int) Math.ceil((double) dao.totalPage() / rows);

		return totalpage;
	}

}
