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
    <h1>
        회원정보 등록양식 
    </h1>
    <form id="rForm" action="user.do" method="post" >
    	<input type="hidden" name="action" value="insert">
        <label>아이디 : </label> <input type="text" id="userid" name="userid" required="required"> 
        	<input type="button" id="duplicateId" value="중복확인">
        <br/>
        <label>비밀번호 : </label>   <input type="password" id="userpassword" name="userpassword" required="required"><br/>
        <label>비밀번호확인 : </label>   <input type="password" id="userpassword2" name="userpassword2" required="required"><br/>
        <label>이름 : </label>   <input type="text" id="username" name="username" required="required"><br/>
        <label>나이: </label>    <input type="number" id="userage" name="userage" required="required"><br/>
        <label>이메일: </label>  <input type="email" id="useremail" name="useremail" required="required"><br/>
    <div>
        <input type="submit" value="등록" >
        <a href="user.do?action=list">취소</a>
    </div>
    
    </form>
    
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
    
    <script type="text/javascript">
    
    const rForm = document.getElementById("rForm");
    const userid = document.getElementById("userid");
    const userpassword = document.getElementById("userpassword");
    const userpassword2 = document.getElementById("userpassword2");
    const username = document.getElementById("username");
    const userage = document.getElementById("userage");
    const useremail = document.getElementById("useremail");
    //아이디 사용 여부 확인 
    let validUserId = "";
    
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다 
    	e.preventDefault();
    	
    	if (validUserId == "" || userid.value != validUserId) {
    		alert("아이디 중복확인 해주세요");
    		return false;
    	}
    	
    	if (userpassword.value != userpassword2.value) {
        	
    		alert("비밀번호가 잘못되었습니다.")
    		userpassword2.value = "";
    		userpassword2.focus();
    		return false;
    	}
    	//fetch를 사용하여 회원 가입을 함
    	//전송자료 구성
    	/*
    	const param = {
			 action : 'insert'
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
				alert("회원가입을 성공 하였습니다");
				location = "user.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});
		*/
		myFetch("user.do", "rForm", json => {
			if(json.status == 0) {
				//성공
				alert("회원가입을 성공 하였습니다");
				location = "user.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});
    });
    
    //id의 객체를 얻는다
	const duplicateId = document.getElementById("duplicateId");
    //click 이벤트 핸들러 등록
	duplicateId.addEventListener("click", () => {
		const userid = document.getElementById("userid");
		if (userid.value == "") {
			alert("아이디를 입력해주세요");
			userid.focus();
			return;
		}
		const param = {
			action : "existUserId",
			userid : userid.value
		}
		fetch("user.do", {
			method:"POST",
			body:JSON.stringify(param),
			headers : {"Content-type" : "application/json; charset=utf-8"}
		}).then(res => res.json()).then(json => {
			//서버로 부터 받은 결과를 사용해서 처리 루틴 구현  
			console.log("json ", json );
			if (json.existUser == true) {
				alert("해당 아이디는 사용 중 입니다.");
				validUserId = "";
			} else {
				alert("사용가능한 아이디 입니다.");
				validUserId = userid.value;
			}
		});
	});
    </script>
    
</body>
</html>