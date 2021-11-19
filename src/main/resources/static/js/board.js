let index={
	init:function(){
		$("#btn-save").on("click", ()=>{ // function(){}을 사용하지 않고 ()=>{} 를 하는 이유는 this를 바인딩하기 위해서임!
			this.save();
		});
		$("#btn-delete").on("click", ()=>{ // function(){}을 사용하지 않고 ()=>{} 를 하는 이유는 this를 바인딩하기 위해서임!
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{ // function(){}을 사용하지 않고 ()=>{} 를 하는 이유는 this를 바인딩하기 위해서임!
			this.update();
		});
		
		/* springsecurity 로 대체 
		$("#btn-login").on("click", ()=>{ // btn-login울 쿨릭하면 login()함수를 호출하라!
			this.login();
		});
		*/
	},
	save:function(){
		//alert('user의 save 함수 호출');
		let data = {
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
		$.ajax({
			type:"POST",
			url:"../api/board",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을떄 응답된 데이터는 기본적으로 모든것이 문자열 (생긴게 json이라면 => javascript 오브젝트로 변경해줌)
		}).done(function(resp){ // 정상이면 실행할 부분 위의 dataType에 따라 resp의 타입이 결정된다. defalt는 JSON 인거같다.
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){ // 실패하면 실행할 부분
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	deleteById:function(){
		let id =  $("#id").text();
		$.ajax({
			type:"DELETE",
			url:"../api/board/"+id,
			dataType: "json" // 요청을 서버로해서 응답이 왔을떄 응답된 데이터는 기본적으로 모든것이 문자열 (생긴게 json이라면 => javascript 오브젝트로 변경해줌)
		}).done(function(resp){ // 정상이면 실행할 부분 위의 dataType에 따라 resp의 타입이 결정된다. defalt는 JSON 인거같다.
			alert("글 삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){ // 실패하면 실행할 부분
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	update:function(){
		let id = $("#id").val();
		
		let data = {
			title:$("#title").val(),
			content:$("#content").val(),
		};
		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을떄 응답된 데이터는 기본적으로 모든것이 문자열 (생긴게 json이라면 => javascript 오브젝트로 변경해줌)
		}).done(function(resp){ // 정상이면 실행할 부분 위의 dataType에 따라 resp의 타입이 결정된다. defalt는 JSON 인거같다.
			alert("글 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){ // 실패하면 실행할 부분
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	
}
index.init();