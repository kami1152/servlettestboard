<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>등록</title>
<style>
label {
	display: inline-block;
	width: 200px;
}

input {
	margin-bottom: 10px;
}

.codybutton {
	margin-top: 40px;
}

.codybutton a {
	display: inline-block;
	padding: 10px 20px;
	margin-right: 10px;
	background-color: #fff; /* 흰색 */
	color: #000; /* 검정색 */
	text-decoration: none;
	border: 2px solid #000; /* 검은색 테두리 */
	border-radius: 5px; /* 모서리를 둥글게 */
	cursor: pointer;
	transition: background-color 0.3s;
}

.codybutton a:hover {
	background-color: #f0f0f0; /* 마우스 호버 시 연한 회색 배경 */
}
</style>
</head>
<body>
	<h1>등록 결과 화면</h1>
	<c:if test="${updated != 0}">
		등록되었습니다 
	</c:if>

	<div class = "codybutton">
		<a href="board.do?action=list">목록</a> <a
			href="board.do?action=view&bno=${param.bno}">상세보기</a>
	</div>
	

</body>
</html>