$(function () {//로드된 후 실행
	getJsonTest();
	
});

function getJsonTest() {                        //data(bike.js)를 msg로 바꿔도 상관없다.
	$.getJSON("resource/json/bike.json",function(data){//$.getJSON은 이경로랑 통신해서 통신할 때 이함수가 실행되면서 이데이터를 가지고 온다.
		
		//결국 $.getJSON은  ajax이다. 
		//jquery가 jason형태의 값을 비동기 통신으로 가져올수 있는 메소드이다. 
	//$.getJSON을 통해서 이경로에 있는 것과 통신할껀데 그통신은 .json파일로 되어있지만 만일 서블릿에 요청을 했을떄 //
		//해당 서블릿이 url이 json값을 object를 리턴해준다고 할떄 이경로가 
		//서블릿(con.do)으로 되도 좋다. 결국은 ajax니까.  .json파일 만 요청을 해서 응답을 받느게 아니라,*응답되는 데이터가 json인 경우에는 어떤 url이던 상관 없다.* 
		$.each(data,function(key,val){//$.each:callback펑션//선택한 요소가 여러개 일때 각각에 대하여 반복하여 함수를 실행 시킨다.
			if(key=="DESCRIPTION"){
				$("table").attr("border","1");
				$("thead").append(
				"<tr>"+
						"<th>"+val.ADDR_GU+"</th>"+
						"<th>"+val.CONTENT_ID+"</th>"+
						"<th>"+val.CONTENT_NM+"</th>"+
						"<th>"+val.NEW_ADDR+"</th>"+
						"<th>"+val.CRADLE_COUNT+"</th>"+
						"<th>"+val.LONGITUDE+"</th>"+
						"<th>"+val.LATITUDE+"</th>"+
				"</tr>"
						
				);
			}else if(key=="DATA"){
				var list=val;//왜 썼나?? 그냥 우리가 사용하는 이터레이브이다.
				for(var i =0;i<list.length;i++){
					var str = list[i];
					$("tbody").append(
						"<tr>"+	"<td>"+i+"</td>"+
								"<td>"+str.addr_gu+"</td>"+
								"<td>"+str.content_id+"</td>"+
								"<td>"+str.content_nm+"</td>"+
								"<td>"+str.new_addr+"</td>"+
								"<td>"+str.cradle_count+"</td>"+
								"<td>"+str.longitude+"</td>"+
								"<td>"+str.latitude+"</td>"+
								"<input type='hidden', name='bike', value='"+
								str.addr_gu+"/"+
								str.content_id+"/"+
								str.content_nm+"/"+
								str.new_addr+"/"+
								str.cradle_count+"/"+
								str.longitude+"/"+
								str.latitude+"'>"+ //서버에 보낼 줄 것 tmp의 0번지
						"</tr>"
					
					
					)
					
				}
				
			}
			
		})
		
	});
}