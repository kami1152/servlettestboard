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
        회원정보 수정양식 
    </h1>
    <form id="rForm" action="user.do" method="post">
    	<input type="hidden" name="action" value="update">
        <label>아이디 : </label> <input type="text" id="userid" name="userid" value="${user.userid}" readonly="readonly"> <br/>
        <label>비밀번호 : </label>   <input type="password" id="userpassword" name="userpassword" required="required"><br/>
        <label>비밀번호확인 : </label>   <input type="password" id="userpassword2" name="userpassword2" required="required"><br/>
        <label>이름 : </label>   <input type="text" id="username" name="username" value="${user.username}"><br/>
        <label>나이: </label>    <input type="text" id="userage" name="userage" value="${user.userage}"><br/>
        <label>이메일: </label>  <input type="text" id="useremail" name="useremail" value="${user.useremail}"><br/>
    <div>
        <input type="submit" value="수정">
        <a href="user.do?action=view&userid=${user.userid}">취소</a>
    </div>
    
    </form>
<!--     
<script type="text/javascript" src="/helloWeb2/js/common.js"></script>
 -->
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>

<script type="text/javascript">
	
const rForm = document.getElementById("rForm");
/*
const userid = document.getElementById("userid");
const userpassword = document.getElementById("userpassword");
const userpassword2 = document.getElementById("userpassword2");
const username = document.getElementById("username");
const userage = document.getElementById("userage");
const useremail = document.getElementById("useremail");
*/
rForm.addEventListener("submit", e => {
	//서버에 form data를 전송하지 않는다 
	e.preventDefault();
	
	if (userpassword.value != userpassword2.value) {
    	
		alert("비밀번호가 잘못되었습니다.")
		userpassword2.value = "";
		userpassword2.focus();
		return false;
	}
	//fetch를 사용하여 회원 정보 수정을 함
	//전송자료 구성
/*	
	const param = {
		 action : 'update'
		,userid : userid.value
    	,userpassword : userpassword.value
        ,username : username.value
        ,userage : userage.value
        ,useremail : useremail.value
	} 
*/	
/*
	fetch("user.do", {
		method:"POST",
		body : formToSerialize("rForm"),
		headers : {"Content-type" : "application/json; charset=utf-8"}
	}).then(res => res.json()).then(json => {
		//서버로 부터 받은 결과를 사용해서 처리 루틴 구현  
		console.log("json ", json );
		if(json.status == 0) {
			//성공
			alert("회원 정보 수정을 성공 하였습니다");
			location = "user.do?action=view&userid=" + userid.value;
		} else {
			alert(json.statusMessage);
		}
	});
*/
	myFetch("user.do", "rForm", json => {
		if(json.status == 0) {
			//성공
			alert("회원 정보 수정을 성공 하였습니다");
			location = "user.do?action=view&userid=" + userid.value;
		} else {
			alert(json.statusMessage);
		}
	});
});

</script>    
</body>
</html>