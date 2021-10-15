let index={
	init:function(){
		$("#btn-save").on("click", ()=>{ // function(){}을 사용하지 않고 ()=>{} 를 하는 이유는 this를 바인딩하기 위해서임!
			this.save();
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
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		// console.log(data);
		// ajax 호출시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 회원가입 수행 요청
			type:"POST",
			url:"../auth/joinProc",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을떄 응답된 데이터는 기본적으로 모든것이 문자열 (생긴게 json이라면 => javascript 오브젝트로 변경해줌)
		}).done(function(resp){ // 정상이면 실행할 부분 위의 dataType에 따라 resp의 타입이 결정된다. defalt는 JSON 인거같다.
			alert("회원가입이 완료되었습니다.");
			//alert(resp);
			//console.log(resp);
			location.href = "/";
		}).fail(function(error){ // 실패하면 실행할 부분
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	
	/* springsecurity 로 대체 
	login:function(){
		//alert('user의 save 함수 호출');
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
		};
		// console.log(data);
		// ajax 호출시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 회원가입 수행 요청
			type:"POST",
			url:"../api/user/login",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=urf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을떄 응답된 데이터는 기본적으로 모든것이 문자열 (생긴게 json이라면 => javascript 오브젝트로 변경해줌)
		}).done(function(resp){ // 정상이면 실행할 부분 위의 dataType에 따라 resp의 타입이 결정된다. defalt는 JSON 인거같다.
			alert("로그인이 완료되었습니다.");
			//alert(resp);
			//console.log(resp);
			location.href = "/";
		}).fail(function(error){ // 실패하면 실행할 부분
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	}
	*/
}
index.init();