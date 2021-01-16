package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MyDao;
import com.mvc.dao.MyDaoImpl;
import com.mvc.dto.MyDto;

public class MyBizImpl implements MyBiz {

	private MyDao dao = new MyDaoImpl();
	
	@Override
	public List<MyDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public MyDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MyDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(MyDto dto) {
	
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		
		return dao.delete(seq);
	}

}
