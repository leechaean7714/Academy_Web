package com.bike.dao;

import static com.bike.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {

	public int insert(List<BikeDto> list) {
		System.out.println(">"+ list.size());
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = " INSERT INTO BIKE_TB VALUES(?,?,?,?,?,?,?) ";

		try {
			pstm = con.prepareStatement(sql);

			for (int i = 0; i < list.size(); i++) {

				BikeDto dto = list.get(i);

				pstm.setString(1, dto.getAddr_gu());
				pstm.setInt(2, dto.getContent_id());
				pstm.setString(3, dto.getContent_nm());
				pstm.setString(4, dto.getNew_addr());
				pstm.setInt(5, dto.getCradle_count());
				pstm.setDouble(6, dto.getLongitude());
				pstm.setDouble(7, dto.getLatitude());

				pstm.addBatch();

			}
			System.out.println("3.쿼리준비" + sql);

			int[] result = pstm.executeBatch();
			System.out.println("4.실행 및 리턴");

			for (int i = 0; i < result.length; i++) {
				if (result[i] == -2) {
					res++;

				}

			}
			if (res == list.size()) {
				commit(con);

			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[error]:3,4");
			e.printStackTrace();
		} finally {

			close(pstm);
			close(con);
		}

		return res;
	}
	
	public int delete() {
		
		Connection con= getConnection();
		
		PreparedStatement pstm=null;
		int res = 0;
		String sql="	DELETE FROM BIKE_TB";
		
		try {
			pstm=con.prepareStatement(sql);
			System.out.println("3.쿼리준비");
			res = pstm.executeUpdate();
			if(res>0) {
				
				commit(con);
			}
			System.out.println("4.실행 및 리턴");
					
		} catch (SQLException e) {
			System.out.println("error:3,4");
			e.printStackTrace();
		}finally {
			
			close(pstm);
			close(con);
			System.out.println("5.db 종료");
		}
		return res;
		
		
		
	}

}
