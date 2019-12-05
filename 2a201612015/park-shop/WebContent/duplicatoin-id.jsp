<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "repository.MemberDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 확인</title>
<% request.setCharacterEncoding("euc-kr"); %>

<%
	String id = request.getParameter("id");
	MemberDAOImpl dao = new MemberDAOImpl();
	boolean check = dao.duplicationID(id);
	
	if (check) {
%>	
	<center>
		<b><font color="red"> <%=id %> </font>는 이미 사용 중인 아이디입니다.</b>
		<form name ="regForm" method = "post" action = "duplicatoin-id.jsp">
			<b>다른 아이디를 입력해주세요.</b><br><br><br>
			<input type="text" name="id"/>
			<input type="submit" value="ID중복확인"/>
		</form>
	</center>
<%
	} else {
%>
	<center>
		<b>입력하신 <font color="red"><%=id %></font>는<br>사욯하실 수 있는 ID입니다.</b>
		<input type="button" value="닫기" onClick="setid()">
	</center>
<%
	}
%>
	<script language="javascript">
		function setid() {
			opener.document.regForm.id.value="<%=id %>";
			self.close();
		}
	</script>

</head>
<body>

</body>
</html>