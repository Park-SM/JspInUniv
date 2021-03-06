    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <header class="main_menu home_menu">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <a class="navbar-brand" href="index.jsp"> <img src="img/logo.png" alt="logo"> </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                            <span class="menu_icon"><i class="fas fa-bars"></i></span>
                        </button>

                        <div class="collapse navbar-collapse main-menu-item" id="navbarSupportedContent">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                	<c:if test="${sessionScope.login_rank == 'admin' }">
                                        	<a class="nav-link" href="member-list.do">Members</a>
                                    </c:if>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="blog.jsp" id="navbarDropdown_1"
                                        role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Shop
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown_1">
                                        <a class="dropdown-item" href="category.jsp"> shop category</a>
                                        <a class="dropdown-item" href="product-list.do">product lists</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="blog.jsp" id="navbarDropdown_3"
                                        role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        pages
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                                    	<c:if test="${sessionScope.login != 'success' }">
                                        	<a class="dropdown-item" href="login.jsp"> login</a>
                                        </c:if>
                                        
                                         <c:choose>
				                            <c:when test="${ sessionScope.login_rank == 'admin' }" >
				                            	<a class="dropdown-item" href="product-register.jsp">product register</a>
				                            </c:when>
				                        </c:choose>
                                        <a class="dropdown-item" href="checkout.jsp">product checkout</a>
                                        <a class="dropdown-item" href="cart.jsp">shopping cart</a>
                                        <a class="dropdown-item" href="confirmation.jsp">confirmation</a>
                                        <a class="dropdown-item" href="elements.jsp">elements</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="blog.jsp" id="navbarDropdown_2"
                                        role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        blog
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                                        <a class="dropdown-item" href="blog.jsp"> blog</a>
                                        <a class="dropdown-item" href="single-blog.jsp">Single blog</a>
                                    </div>
                                </li>
                                
                                <li class="nav-item">
                                    <a class="nav-link" href="contact.jsp">Contact</a>
                                </li>
                            </ul>
                        </div>
                        <div class="hearer_icon d-flex">
                            <a id="search_1" href="javascript:void(0)"><i class="ti-search"></i></a>
                            
                            <c:choose>
	                            <c:when test="${sessionScope.login != 'success'}" >
	                            	<a href="login.jsp"><i class="ti-heart-broken"></i></a>
	                            </c:when>
	                            <c:otherwise>
	                            	<a href='confirm-pw.jsp?id=${ sessionScope.login_id }'><i class="ti-user"></i></button>
	                            	<a href="logout.jsp"><i class="ti-arrow-right"></i></a>
	                            </c:otherwise>
                            </c:choose>

                     
                            
                        <%---
                            <%
                            	String loginInfo = (String) session.getAttribute("login");
                            	if (loginInfo != null && loginInfo.equals("success")) {
                            %>
                            		<a href="info.jsp"><i class="ti-heart"></i></a>
                            		<a href="logout.jsp"><i class="ti-arrow-right"></i></a>
                            <%
                            	} else {
                            %>
                            		<a href="login.jsp"><i class="ti-heart-broken"></i></a>
                            <%
                            	}
                            %>
                        --%>
                            
                            <div class="dropdown cart">
                                <a class="dropdown-toggle" href="./product-list.do" id="navbarDropdown3" role="button">
                                    <i class="fas fa-cart-plus"></i>
                                </a>
                                <!-- <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <div class="single_product">
    
                                    </div>
                                </div> -->
                                
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>