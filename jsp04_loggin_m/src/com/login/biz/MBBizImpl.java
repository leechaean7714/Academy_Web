package com.login.biz;

import java.util.List;

import com.login.dao.MBDao;
import com.login.dao.MBDaoImpl;
import com.login.dto.MBDto;

public class MBBizImpl implements MBBiz {
	
	private MBDao dao = new MBDaoImpl();

	@Override
	public List<MBDto> selectList() {
		return dao.selectList();
	}

	@Override
	public List<MBDto> selectEnabled() {
		return dao.selectEnabled();
	}

	@Override
	public int updateRole(int myno, String role) {
		return dao.updateRole(myno, role);
	}

	@Override
	public MBDto login(String myid, String mypw) {
		return dao.login(myid, mypw);
	}

	@Override
	public MBDto idChk(String myid) {
		return dao.idChk(myid);
	}

	@Override
	public int insertUser(MBDto dto) {
		return dao.insertUser(dto);
	}

	@Override
	public MBDto selectUser(int myno) {
		return dao.selectUser(myno);
	}

	@Override
	public int updateUser(MBDto dto) {
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(int myno) {
		return dao.deleteUser(myno);
	}

}
