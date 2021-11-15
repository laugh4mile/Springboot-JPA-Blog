<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<c:forEach var="board" items="${boards}"> <!-- BoardController에서 뿌려준 모델 (boards)를 items로 받아서 변수 board에 하나씩 담아서 뿌림 -->
	<div class="container">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4> <!-- ${board.title}라고 하면 Board dto의 getTitle()이 호출된다. 나는 lombok으로 했다. (@Data) -->
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
</c:forEach>


<%@ include file="layout/footer.jsp"%>

