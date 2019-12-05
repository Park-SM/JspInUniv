<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>스크립틀릿(Scriptlet) : <% out.println(request.getParameter("name")); %> </h1>
	<h1>표현식(Expression): <%= request.getParameter("password") %></h1>
	
	<%--- request.getAttribute()의 return value는 Object class임. -> String class로 캐스팅 --%>
	<h1>속성 이름(Option name): <%= (String)request.getAttribute("get1") %></h1>
	
	<c:forEach begin = "1" end = "10">
		<%--- requestScope.get1 get1 변수를 찾는 곳은 크게 4가지 그중 requestScope로 지정해서 선언함. --%>
		<h1>EL: ${ get1 }</h1>
	</c:forEach>
	
</body>
</html>