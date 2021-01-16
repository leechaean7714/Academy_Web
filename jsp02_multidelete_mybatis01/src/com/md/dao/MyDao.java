package com.md.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.md.dto.MyDto;

public class MyDao extends SqlMapConfig {

	private String namespace = "muldel.";

	public List<MyDto> selectList() {
		List<MyDto> list = null;

		SqlSession session = null;

		try {

			session = getSqlSessionFactory().openSession();
			list = session.selectList("muldel.selectList");
			
		} catch (Exception e) {

			System.out.println("error:selectList");
		} finally {

			session.close();
		}

		return list;
	}

	public int multiDelete(String[] no) {

		int res = 0;

		SqlSession session = null;

		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("nos", no);

		try {

			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace + "muldel", map);

			if (res == no.length) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("error:muldel");
		} finally {
			session.close();
		}

		return res;
	}

	public MyDto selectOne(int no) {

		MyDto dto = new MyDto();
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOne", no);

		} catch (Exception e) {
			System.out.println("error:selectOne");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dto;
	}

	public int insert(MyDto dto) {
		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);// 오토 커밋이 안된다
			res = session.insert(namespace + "insert", dto);

			if (res > 0) {
				session.commit();
			}

		} catch (Exception e) {
			System.out.println("error:insert");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

	public int update(MyDto dto) {
		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace + "update", dto);

			if (res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("error:update");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return res;
	}

	public int delete(int no) {

		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace + "delete", no);
			if (res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("error:delete");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return res;
	}
}
