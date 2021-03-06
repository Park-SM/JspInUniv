<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="domain.Product"%>
<%@ page import="repository.ProductDAOImpl"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품 목록</title>
</head>
<body>
	<jsp:include page="pheader.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>

	<div class="container">
		<div class="row" align="center">
			<c:forEach var="p" items="${ requestScope.productList }">
				<div class = "col-md-4">
					<h3>${ p.pname }</h3>
					<p>${ p.description }</p>
					<p>${ p.unitPrice } 원</p>
					<p><a href="./product-detail.do?id=${ p.productId }" class="btn btn-secondary" role="button"> 상세 정보 &raquo; </a>
				</div>	
			</c:forEach>
		</div>
		<hr>
	</div>
	<jsp:include page="pfooter.jsp" />
</body>
</html>
