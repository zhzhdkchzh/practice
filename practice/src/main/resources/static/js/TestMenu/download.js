$(document).ready(function(){
	$(".parent-btn").click(function() {
		$(".parent-btn").parent().css({ "background": "skyblue" })	//버튼의 부모요소 css수정
	})
	$(".parents-btn").click(function() {
		$(".parents-btn").parents("div").css({ "background": "pink" })	//버튼의 조상 요소중 div css수정 
	})
	$(".parentsUntil-btn").click(function() {
		$(".parentsUntil-btn").parentsUntil($("#parent1")).css({ "background": "green" })	//버튼~지정대상의 자식요소 css수정
	})
	$(".init-btn").click(function() {
		$(".parent").css({ "background": "white" })				//초기화
	})
	$(".error-btn").click(function(){				//ajax error 테스트 버튼
		$.ajax({		
			url: "/321",						//통신에러를 내기위해 정의되지않은 url요청			
			type: 'POST',
			success:function(data){
				alert(data);
			},
			error:function( jqXHR, textStatus, errorThrown ){
				alert(jqXHR.status);			//status : http 오류 번호 를 출력  (500, 404 등)
				alert(jqXHR.statusText);		//statusText : 오류 내용 텍스트 -> 세번째 인자 errorThrown과 동일
				alert(jqXHR.responseText);		//responseText : url의 response full text를 출력 
				alert(jqXHR.readyState);		//readyState : ajax readyState를 출력 
			}
		})
	})
	$(".test-btn").click(function(){
		OnAjax.getData("/testAjax", "post", JSON.stringify({test: "요청데이터"}), function(result){
			alert(result.test);
		})
	})
})


