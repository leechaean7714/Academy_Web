function load() {

	var url = getParameterValues();

	// 변수의 호이스팅 공부하기
	httpRequest = new XMLHttpRequest();          //httpRequest(변수) var를 안 씀.변수의 호이스팅 공부하기//서버와 통신
	httpRequest.onreadystatechange = callback;   //readystate가 change될때  on(이벤트)할꺼야 callback함수를 실행해주세요.
	//처리할 함수 callback(요청하면 처리하고 다시 되돌아온다.그리도 다음꺼 계속하면 된다. ) 공부하기//요청하면 처리하고 돌아갈꺼다
	httpRequest.open("GET", url, true);//GET:get방식,true:비동기, false(동기)
	httpRequest.send();//get방식:send()/post:send(String(데이타))
    //load될때 XMLHttpRequest객체가 만들어져서  XMLHttpRequest객체가 onreadystatechange될때마다 callback함수를 호출해서 
	//그 callback함수가 1,2,3,4,200,함수가 반복해서 만들어 진다. 연결해서 통신해서 보내서 받아서 그걸 받아서 썼다-->$.ajax()
}

/*
 * XMLHttpRequest:서버와 통신을 도와주는 객체. http를  통한 데이터 송수신 지원.(javascript object) 
 *                이것을 가지고 비동기 통신을 한다.
 * -readystate
 * 0; uninitialized -실행 되지(load)않음
 * 1:loading- 로드중 
 * 2:loaded-로드됨
 * 3:interactive-통신 됨
 * 4:complete-통신 완료
 * 
 * --status:통신 완료됬을떄 그결과가 어떻게 되냐??
 * 200:성공
 * 401:unauthorized
 * 400:bad request
 * 403:forbidden
 * 404:not found
 * 500:internal sever error
 * 
 * --encodeURIComponent()모든 문자 인코딩.
 * --encodeURI()주소줄에 사용되는 특수문자(?$)는 제외하고 인코딩.
 * --decodeURIComponent :모든 문자를 다시 decoding
 * --JSON.parse():json()을 객체로 바꾸는것을 해준다.
 */

		function callback() {
			alert("readyState:" + httpRequest.readyState);
			
			if (httpRequest.readyState == 4) {
		
				alert("status:" + httpRequest.status);
		
				if (httpRequest.status == 200) {//success이면
					var obj = JSON.parse(httpRequest.responseText);//요청해서 응답받은거 가지고 화면에 만들어 주세요.
		
					document.getElementById("result").innerHTML = decodeURIComponent(obj.name)
							+ "님의 총점은" + obj.sum + "이고, 평균은" + obj.avg + "입니다.";
				} else {
					alert("통신실패");
		
				}
			}
		
		}

		function getParameterValues() {
			var name = "name="+ encodeURIComponent(document.getElementById("name").value);
			
			var kor = "kor=" + document.getElementById("kor").value;
			var eng = "eng=" + document.getElementById("eng").value;
			var math= "math="+ document.getElementById("math").value;
		
			var url = "score.cal?" + name + "&" + kor + "&" + eng + "&" + math;
			console.log(url);
			return url;
		
		}