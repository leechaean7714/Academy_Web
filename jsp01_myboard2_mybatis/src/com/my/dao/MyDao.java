package com.my.dao;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.dto.MyDto;

public class MyDao {//mybatis:JDBC처럼 공통적인 것을 만들어 놓은 것이다.JDBC로 처리하는 상당부분의 코드와 파라미터 설정및 결과 매핑을 대신해준다.

	public List<MyDto> selectList() {
		//resource:
		String resource = "com/my/db/mybatis-config.xml";// sql 세션 팩토리 객체필요->빌더가 만들어주고->xml파일을 읽어다가 만들어 준다.
		InputStream inputStream = null;                  //InputStream:프로그램 끼리의 데이터 입출력 

		try {
			inputStream = Resources.getResourceAsStream(resource);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		//SqlSessionFactory인스턴스는 SqlSessionFactoryBuilder를 사용하여 만들수 있다. SqlSessionFactoryBuilder는 XML설정파일이 필요하다.
		//SqlSessionFactory:데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//SqlSession 인스턴스:데이터베이스에 대해 SQL명령어를 실행하기 위해 필요한 모든 메소드를 가지고 있다.
		SqlSession session = sqlSessionFactory.openSession();

		List<MyDto> list = session.selectList("com.my.mapper.selectList");

		session.close();

		return list;

	}

	
	
	
	public MyDto selectOne(int myno) {

		String resource = "com/my/db/mybatis-config.xml";

		InputStream inputStream = null;
		try {

			inputStream = Resources.getResourceAsStream(resource);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		MyDto dto = session.selectOne("com.my.mapper.selectOne", myno);
		session.close();

		return dto;

	}

	public int insert(MyDto dto) {

		String sql = "com/my/db/mybatis-config.xml";

		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(sql);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		int res = sqlSession.insert("com.my.mapper.insert", dto);

		if (res > 0) {

			sqlSession.commit();
		}
		sqlSession.close();

		return res;

	}

	public int update(MyDto dto) {
		String sql = "com/my/db/mybatis-config.xml";

		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(sql);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		int res = sqlSession.update("com.my.mapper.update", dto);

		/*
		 * if (res > 0) {
		 * 
		 * sqlSession.commit(); } sqlSession.close();
		 */
		return res;

	}

	public int delete(int myno) {
		String sql = "com/my/db/mybatis-config.xml";

		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(sql);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		int res = sqlSession.delete("com.my.mapper.delete", myno);

		if (res > 0) {

			sqlSession.commit();
		}
		    sqlSession.close();

		return res;

		
	}
}
