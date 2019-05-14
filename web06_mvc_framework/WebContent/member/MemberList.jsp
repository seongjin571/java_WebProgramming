<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.List" %>
<%@ page import ="spms.dto.Member" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
	
		<c:forEach var="member" items="${requestScope.members}">
				${member.no},
				<a href='update.do?no=${member.no}'>${member.name}</a>,
				${member.email},
				${member.createDate}
				<a href='delete.do?no=${member.no}'>[삭제]</a><br>
		</c:forEach>
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>