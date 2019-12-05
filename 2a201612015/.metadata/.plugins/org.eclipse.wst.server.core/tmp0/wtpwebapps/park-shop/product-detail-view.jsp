<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Product"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 상세 정보</title>
</head>
<body>
	<jsp:include page="pheader.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 정보</h1>
		</div>
	</div>
	<%
		Product product = (Product) request.getAttribute("product");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<img src="./a-upload/<%=product.getFilename()%>" style="width: 100%" />
			</div>
			<div class="col-md-6">
				<h3><%=product.getPname()%></h3>
				<p><%=product.getDescription()%>
				<p><b>상품 코드 : </b><span class="badge badge-danger"> <%=product.getProductId()%></span>
				<p><b>제조사</b> : <%=product.getManufacturer()%>
				<p><b>분류</b> : <%=product.getCategory()%>
				<p><b>재고 수</b> : <%=product.getUnitsInStock()%>
				<h4><%=product.getUnitPrice()%>원</h4>
				<p><a href="cart-add.do?id=<%=product.getProductId() %>" onclick='return confirm("해당 상품을 추가하시겠습니까?");' class="btn btn-info"> 상품 주문 &raquo;</a>
				<a href="./product-edit.do?id=<%=product.getProductId() %>" class="btn btn-secondary"> 상품 수정 &raquo;</a>
				<%
					if (((String)session.getAttribute("login_id")).equals("admin")) {
				%>
						<a href="./product-remove.do?id=<%=product.getProductId() %>" class="btn btn-secondary"> 상품 삭제 &raquo;</a>
				<%
					}
				%>
				<a href="./product-list.do" class="btn btn-secondary"> 상품 목록 &raquo;</a>
			</div>
		</div>
		<hr>
	</div>
	<jsp:include page="pfooter.jsp" />
</body>
</html>
