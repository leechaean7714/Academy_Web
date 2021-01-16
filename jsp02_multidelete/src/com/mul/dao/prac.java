package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mul.db.JDBCTemplate;
import com.mul.dto.MyDto;

public class prac extends JDBCTemplate {

	public int insert(MyDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = "insert into mdboardprac values(mdboardpracseq.nextval,?,?,?,sysdate)";
		try {
			pstm = con.prepareStatement(sql);
			// pstm.setString(1, dto.);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;

	}

}
