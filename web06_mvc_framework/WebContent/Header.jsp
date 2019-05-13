<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Member"%>

<jsp:useBean id="member" scope="session" class="dto.Member" type="dto.Member"></jsp:useBean>
<div style="background-color:#00008b; color:#ffffff; height:20px; padding:5px;">
	SPMS(Simple Project Management System)
	<%if(member.getEmail() != null){ %>
	<span style="float:right">
	    <%=member.getName()%>
	    <a style="color: white" href="<%=request.getContextPath()%>/auth/logout">로그아웃</a>
	</span>
	<% }%>
</div>
