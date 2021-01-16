package com.mvc.dao;

import java.io.IOException;
import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sqlsessionFactory;

	public SqlSessionFactory sqlSession() {
		String resource = "com/mvc/db/mybatis_config.xml";
		try {
			InputStream input = Resources.getResourceAsStream(resource);
			sqlsessionFactory = new SqlSessionFactoryBuilder().build(input);
			input.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return sqlsessionFactory;

	}

}
