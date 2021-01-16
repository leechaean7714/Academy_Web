package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MVCDto;
import com.mvc.dto.PagingDto;

public interface MVCBIz {
	
	public List<MVCDto> selectList();
	public MVCDto selectOne(int seq);
	public int insert(MVCDto dto);
	public int update(MVCDto dto);
	public int delete(int seq);
	public int totalPage(int rows);
	

}
