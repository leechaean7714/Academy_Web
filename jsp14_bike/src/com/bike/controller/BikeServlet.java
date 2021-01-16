package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet()
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String command = request.getParameter("command");

		BikeDao dao = new BikeDao();
		dao.delete();

		if (command.equals("first")) {

			response.sendRedirect("bike01.jsp");

		} else if (command.equals("firstdb")) {
			String[] bike = request.getParameterValues("bike");// getParameterValues:똑같은 이름의 데이터를 받을 수 있다.

			List<BikeDto> list = new ArrayList<BikeDto>();

			for (int i = 0; i < bike.length; i++) {
				String[] tmp = bike[i].split("/");// "/":String 배열이 나온다.//split()은 문자열을 분할하는 메서드입니다.

				// System.out.println(tmp[2] + "," + tmp[5] + "," + tmp[6]);
				// 1.tmp를 dto에 담자

				BikeDto dto = new BikeDto(tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3], Integer.parseInt(tmp[4]),
						Double.parseDouble(tmp[5]), Double.parseDouble(tmp[6]));

				// 2.dto를 list(bikes)에 담자.
				list.add(dto);

			}
			System.out.println(list.size());

			// 3.dao에 list를 보내서 insert하자.
			int res = dao.insert(list);
			if (res == list.size()) {
				System.out.println("성공");
			} else {

				System.out.println("실패");
			}

			// 4.저장 완료되면 index.html로 보내자.
			response.sendRedirect("index.html");

		} else if (command.equals("second")) {

			response.sendRedirect("bike02.jsp");

		} else if (command.equals("second_db")) {
			String txt = request.getParameter("obj");

			// System.out.println(txt); 이게 나온다.{"DESCRIPTION":{"NEW_ADDR":"대여소
			// 주소","CONTENT_ID":"대여소번호","ADDR_GU":"구분
			JsonElement element = JsonParser.parseString(txt);// String을 json으로 바꿔준다.일단 타입은 모른다.
			// System.out.println(element.getAsJsonObject().get("DESCRIPTION")); 공부하기

			List<BikeDto> list = new ArrayList<BikeDto>();

			for (int i = 0; i < element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				// 전체/ 일단 jsonobject로 가지고 올꺼야 {}:무엇으로 오는지
				// 모르니까/get(key(data))/getAsJsonObject().get("DATA")까지 element/
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				// System.out.println(tmp.get("addr_gu").getAsString()); //중랑구 //get(i):element로
				// 온다.
				// 가지고 온게 뭔지 모른다
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				//
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();

				BikeDto dto = new BikeDto(addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				list.add(dto);

			}

			int res = dao.insert(list);
			if (res == 1163) {
				System.out.println("insert성공");

			} else {
				System.out.println("insert실패");
			}

			response.getWriter().append(res + "");
		}
	}
}
