<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
    <style>
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
    </style>
</head>
<body>
	<h1>게시글</h1>

    
    <form id="listForm" action="board.do" method="post">
    	<input type="hidden" id="action" name="action" value="view">
    	<input type="hidden" id="bno" name="bno" >
    </form>

    <table border="1">
        <tr>
            <th>bno</th>
            <th>btitle</th>
            <th>bwriter</th>
            <th>bdate</th>
        </tr>
        <c:forEach var="board" items="${list}">
        <tr>
            <td onclick="jsView('${board.bno}')"  style="cursor:pointer;">${board.bno}</td>
            <td>${board.btitle}</td>
            <td>${board.bwriter}</td>
            <td>${board.bdate}</td>
        </tr>
        </c:forEach>
    </table>
<script>
function jsView(uid) {
	//인자의 값을 설정한다 
	bno.value = uid;
	
	//양식을 통해서 서버의 URL로 값을 전달한다
	listForm.submit();
	
}
</script>      
    <div class="button-container">
        <a href="board.do?action=insertForm">등록</a>
    </div>
</body>
</html>