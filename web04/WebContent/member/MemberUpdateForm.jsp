<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>  
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>회원 수정</title>
	</head>
	<% Member member = (Member)request.getAttribute("member"); %>
	<body>
		<h1>회원 수정</h1>
		<form>
			번호: <input type='text' name='mno' value='<%= member.getNo() %>'><br>
			이름: <input type='text' name='name' value='<%= member.getName() %>'><br>
			이메일: <input type='text' name='email'value='<%= member.getEmail()%>'><br>
			암호: <input type='password' name='password' value ='<%= member.getPassword() %>'><br>
			가입일: <span><%= member.getCreateDate() %></span><br>
			<input type='submit' value='수정'>
			<input type='reset' value='취소'>
		</form>
	</body>
</html>
	