package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MVCDto;

public interface MVCBiz {
//interface : 그대상이 되는 시스템을 제어 한다.
//외부에서 조작하기 위해서 public 이다.

	public List<MVCDto> selectList();
	public MVCDto selectOne(int seq);
	public int insert(MVCDto dto);//dto가 넘어와야 한다.
	public int update(MVCDto dto);
	public int delete(int seq);
	public boolean multiDelete(String[] seqs);
}
  