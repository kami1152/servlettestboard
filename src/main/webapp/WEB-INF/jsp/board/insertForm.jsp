<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>등록화면</title>
<style>
label {
	display: inline-block;
	width: 120px;
}

input {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<h1>글 등록양식</h1>
	<form id="rForm" action="board.do">
		<input type="hidden" name="action" value="insert"> <label>제목:
		</label> <input type="text" id="btitle" name="btitle"> <br /> <label>내용
			: </label> <input type="text" id="bcontent" name="bcontent"><br /> <label>글쓴이
			: </label> <input type="text" id="bwriter" name="bwriter"><br />
		<div>
			<input type="submit" value="등록"> <a
				href="board.do?action=list">취소</a>
		</div>

	</form>
	<script type="text/javascript">
		
	</script>

	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
	<script type="text/javascript">
		const rForm = document.getElementById("rForm");
		rForm.addEventListener("submit" , e => {
			e.preventDefault();
			
			myFetch("board.do" , "rForm" , json=>{
				if(json.status == 0){
					alert("성공")
					location = "board.do?action=list";
				}
			});
		});
	</script>

</body>


</html>