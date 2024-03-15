<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>수정화면</title>
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
    <h1>
      	게시글 수정
    </h1>
    <form action="board.do">
    	<input type="hidden" name="action" value="update">
        <label>아이디 : </label> <input type="text" id="bno" name="bno" value="${board.bno}" readonly="readonly"> <br/>
        <label>내용 : </label>   <input type="text" id="bcontent" name="bcontent" value=""><br/>
    <div>
        <input type="submit" value="수정">
        <a href="board.do?action=view&bno=${board.bno}">취소</a>
    </div>
    
    </form>
    
</body>
</html>