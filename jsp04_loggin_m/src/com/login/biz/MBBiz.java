package com.login.biz;

import java.util.List;

import com.login.dto.MBDto;

public interface MBBiz {
	
	// 관리자(MYROLE = ADMIN) 기능
	// 1.회원 전체 정보 (탈퇴회원 포함)
	public List<MBDto> selectList();
	// 2.회원 전체 정보 (가입된 회원들만 : myenabled = y
	public List<MBDto> selectEnabled();
	// 3.회원 등급 조정
	public int updateRole(int myno, String role);
	
	// 사용자(MYROLE = USER) 기능
	// 1.로그인
	public MBDto login(String myid, String mypw);
	
	// 2.회원가입
	// 2-1.UNIQUE 속성들의 중복체크
	public MBDto idChk(String myid);
	// 2-2.회원가입
	public int insertUser(MBDto dto);
	// 3.내 정보 조회
	public MBDto selectUser(int myno);
	// 4.내 정보 수정
	public int updateUser(MBDto dto);
	// 5.회원탈퇴 : update myenabled = 'n'
	public int deleteUser(int myno);


}
