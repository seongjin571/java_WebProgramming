<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.dto.Member" %>  
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>회원 수정</title>
	</head>
	<body>
		<h1>회원 수정</h1>
		<form action="update.do" method="post">
			번호: <input type='text' name='no' value='${requestScope.member.no}'><br>
			이름: <input type='text' name='name' value='${requestScope.member.name}'><br>
			이메일: <input type='text' name='email'value='${requestScope.member.email}'><br>
			암호: <input type='password' name='password' value ='${requestScope.member.password}'><br>
			가입일: <span>${requqestScope.member.createDate}</span><br>
			<input type='submit' value='수정'>
			<input type='reset' value='취소'>
		</form>
	</body>
</html>
	