package com.mvc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mvc.dto.MyDto;

public class MyDaoImpl extends SqlMapConfig implements MyDao {

	@Override
	public List<MyDto> selectList() {
		List<MyDto> list = null;
		SqlSession session = null;
		try {
			session = sqlSession().openSession();
			list = session.selectList("com.mvc.db.mapper.selectlist");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MyDto selectOne(int seq) {
		MyDto dto = null;
		SqlSession session = null;
		try {
			session = sqlSession().openSession();
			dto = session.selectOne("com.mvc.db.mapper.selectone", seq);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int insert(MyDto dto) {

		SqlSession session = sqlSession().openSession();
		int res = session.insert("com.mvc.db.mapper.insert", dto);
		if (res > 0) {
			session.commit();
		}
		session.close();
		return res;
	}

	@Override
	public int update(MyDto dto) {

		SqlSession session = sqlSession().openSession();
		int res = session.update("com.mvc.db.mapper.update", dto);
		if (res > 0) {
			session.commit();
		}
		session.close();
		return res;
	}

	@Override
	public int delete(int seq) {

		SqlSession session = sqlSession().openSession();
		int res = session.delete("com.mvc.db.mapper.delete", seq);
		if (res > 0) {
			session.commit();
		}
		session.close();
		return res;
	}

}
