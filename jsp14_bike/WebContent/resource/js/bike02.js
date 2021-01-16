$(function () {
	
	parseJsonTest();
	
});




function parseJsonTest(){
	$.getJSON("resource/json/bike.json",function(data){// json 파일의 데이터를 로드해옴,
														// 요청이 성공하면 function안에
														// data에 넣어줌
		$.ajax({// 비동기 통신, 서블렛으로 보내서 저장하고 저장되면 화면에 표시할 예정
			url:"bike.do?command=second_db", // 요청할 url
			method:"post", // 통신 타입
			data:{"obj":JSON.stringify(data)}, // 데이터를 json문자열로 변환하여 보내줌
			
			success:function(msg){// msg:ajax가 요청해서 응답받은 text(data타입이
									// 정해지지않았기에), 성공했을때 콜백
				
				if(msg==1163){
					// table에 표시하자
					$.each(data,function(key,val){ // each반복문을 사용, 내용을 하나씩 읽어서
													// key,val에 데이터를 넣음
						if(key=="DESCRIPTION"){
							$("table").attr("border","1");// 속성 추가 table 요소에
															// border 속성을 추가하고
															// 속성의 값은 1
							
							var $tr=$("<tr>");
							for(var i in val){
								$tr.append($("<th>").html(val[i]));
							}
							$("thead").append($tr);
						}else{
							for(var i=0;i<val.length;i++){
								var $tr=$("<tr>");
								for(var j in val[i]){
									$tr.append($("<td>").html(val[i][j])    );
								}
								$("tbody").append($tr);
							}
						}
						
						});
					
				}else{
					alert("저장 실패");
				}
			},
			    error:function(){
				alert("통신 실패");
			}
		});
	});
}