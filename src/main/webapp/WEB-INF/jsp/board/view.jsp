<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>board insert form</title>
<style>
label {
	display: inline-block;
	width: 200px;
}

input {
	margin-bottom: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #f5f5f5;
}

.button-container {
	margin-top: 20px;
}

.button-container button {
	padding: 10px 20px;
	font-size: 16px;
	background-color: #f5f5f5;
}

.codybutton {
	margin-top: 20px;
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
	<h1>게시글 세부내용3</h1>
	<table border="1">
		<tr>
			<th>bno</th>
			<th>btitle</th>
			<th>bcontent</th>
			<th>bwriter</th>
			<th>bdate</th>
		</tr>
		<tr>
			<td>${board.bno}</td>
			<td>${board.btitle}</td>
			<td>${board.bcontent}</td>
			<td>${board.bwriter}</td>
			<td>${board.bdate}</td>
	</table>

	<!-- 두개의 폼을 하나로 합치는 방법 , js를 사용하여 처리  -->

	<form id="viewForm" method="post" action="board.do">
		<input type="hidden" id="action" name="action" value=""> <input
			type="hidden" id="bno" name="bno" value="${board.bno}"> <input
			type="button" value="삭제" onclick="jsDelete()"> <input
			type="button" value="수정" onclick="jsUpdateForm()">
	</form>
</body>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>
function jsDelete() {
	if (confirm("정말로 삭제하시겠습니까?")) {

		action.value = "delete";
		myFetch("board.do", "viewForm", json => {
			if(json.status == 0) {
				//성공
				alert("회원정보를 삭제 하였습니다");
				location = "board.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});

	}
}
function jsUpdateForm() {
	if (confirm("정말로 수정하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "updateForm";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}	
}
</script>

</html>