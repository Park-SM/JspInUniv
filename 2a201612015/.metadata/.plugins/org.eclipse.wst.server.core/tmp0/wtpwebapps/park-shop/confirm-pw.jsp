<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "repository.MemberDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW 확인</title>

<% request.setCharacterEncoding("euc-kr"); %>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDAOImpl dao = new MemberDAOImpl();
	if (pw == null) {
%>	
	<center>
		<b>암호를 입력해주세요.</b>
		<form name ="regForm" method = "post" action = "confirm-pw.jsp"><br>
			<input type="hidden" name="id" value="<%=id %>">
			<input type="password" name="pw"/>
			<input type="submit" value="PW확인"/>
		</form>
	</center>
	
<%
	} else {
		if (dao.confirmID(id, pw)) {
%>
	<script>
		{
			location.href='member-info?id=<%=id%>&pw=<%=pw%>';
		}
	</script>
<%
		} else {
%>
	<center>
		<b>암호가 올바르지 않습니다.</b>
		<form name ="regForm" method = "post" action = "confirm-pw.jsp">
			<b>암호를 다시 입력해주세요</b><br><br><br>
			<input type="hidden" name="id" value="<%=id %>">
			<input type="password" name="pw"/>
			<input type="submit" value="PW확인"/>
		</form>
	</center>

<%
		}
	}
%>


</head>
<body>

</body>
</html>