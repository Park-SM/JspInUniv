<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Product"%>
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<title>상품 수정</title>
</head>
<body>
	<jsp:include page="pheader.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 수정</h1>
		</div>
	</div>
	<%
		Product product = (Product) request.getAttribute("product");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<img src="./a-upload/<%=product.getFilename() %>" alt="image" style="width: 100%" />
			</div>
			<div class="col-md-7">
				<form name="editProduct" action="./product-update.do" class="form-horizontal" method="post" enctype="multipart/form-data">
					<div class="form-group row">
						<label class="col-sm-2">상품 코드</label>
						<div class="col-sm-3">
							<input type="text" id="productId" name="productId" class="form-control" value='<%=product.getProductId() %>' readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">상품명</label>
						<div class="col-sm-3">
							<input type="text" id="name" name="name" class="form-control" value="<%=product.getPname() %>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">가격</label>
						<div class="col-sm-3">
							<input type="text" id="unitPrice" name="unitPrice" class="form-control" value="<%=product.getUnitPrice()%>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">상세 설명</label>
						<div class="col-sm-5">
							<textarea name="description" cols="50" rows="2" class="form-control"><%=product.getDescription() %></textarea>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">제조사</label>
						<div class="col-sm-3">
							<input type="text" name="manufacturer" class="form-control" value="<%=product.getManufacturer()%>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">분류</label>
						<div class="col-sm-3">
							<input type="text" name="category" class="form-control" value="<%=product.getCategory() %>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">제고 수</label>
						<div class="col-sm-3">
							<input type="text" id="unitsInStock" name="unitsInStock" class="form-control" value="<%=product.getUnitsInStock() %>">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">상태</label>
						<div class="col-sm-5">
							<%
								String condition = product.getCondition();
								if (condition.equals("New")) {
							%>
									<input type="radio" name="condition" value="New" checked> 신규 제품 
									<input type="radio" name="condition" value="Old" > 중고 제품 
									<input type="radio" name="condition" value="Refurbished" > 재생 제품
							<%
								} else if (condition.equals("Old")) {
							%>
									<input type="radio" name="condition" value="New" > 신규 제품 
									<input type="radio" name="condition" value="Old" checked> 중고 제품 
									<input type="radio" name="condition" value="Refurbished" > 재생 제품
							<%
								} else if (condition.equals("Refurbished")) { 
							%>
									<input type="radio" name="condition" value="New" > 신규 제품 
									<input type="radio" name="condition" value="Old" > 중고 제품 
									<input type="radio" name="condition" value="Refurbished" checked> 재생 제품
							<%
								}
							%>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2">이미지</label>
						<div class="col-sm-5">
							<input type="file" name="filename" class="form-control">
							<input type="hidden" name="oldFilename" value="<%=product.getFilename() %>">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-offset-2 col-sm-10 ">
							<input type="submit" class="btn btn-primary" value="등록">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="pfooter.jsp" />
</body>
</html>
