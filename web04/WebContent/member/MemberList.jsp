<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.List" %>
<%@ page import ="dto.Member" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<%
			List<Member> members = (ArrayList<Member>)request.getAttribute("members");
			for(Member member : members){
		%>
				<%=member.getNo()%>,
				<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
				<%=member.getEmail()%>,
				<%=member.getCreateDate()%>
				<a href='delete?no=<%=member.getNo()%>'>[삭제]</a><br>
		<%}%>
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>