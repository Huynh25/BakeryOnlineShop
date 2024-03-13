<%-- 
    Document   : view-rating
    Created on : Feb 20, 2024, 9:08:48 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Rating</title>
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/product-history.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    </head>

    <body>
        <%@include file="../homeviews/Header.jsp" %>
        <div id="content">            
            <div id="url">
                <a id="home-link" href="#">Home</a>
                <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                <span id="title">Product History Management</span>
                <c:if test="${sessionScope.user.role eq 'manager'}">
                    <div id="statistic-link-wrapper">                       
                        <a href="../../views/adminviews/product-history-statistic-page.jsp" id="statistic-link">Statistics <i class="bi bi-bar-chart"></i></a>
                    </div>
                </c:if>
            </div>
            <nav id="nav-bar-container" >
                <div id="nav-bar" class="row">                   
                    <ul class="row col-sm-4">
                        <li class="col-sm-4 nav-bar--active" id="all-filter"><button disabled class="nav-bar-items" onclick="performFilter('all')">All</button></li>
                        <li class="col-sm-4" id="cake-filter"><button class="nav-bar-items"  onclick="performFilter('cake')">Cake</button></li>
                        <li class="col-sm-4" id="topping-filter"><button class="nav-bar-items"  onclick="performFilter('topping')">Topping</button></li>                   
                    </ul>
                    <form id="search-product-form" onsubmit="return false;" class="col-sm-4">
                        <input id="search-product-input" type="text" placeholder="Search by Cake,Topping or Staff"/>
                        <button onclick="performFilter('search')"><i class="bi bi-search"></i></button>
                    </form>
                    <form class="col-sm-3" id="filterDate"> 
                        Date:&nbsp
                        <input id="date-filter-value" type="date" onchange="performFilter('date')">
                    </form>
                    <div class="col-sm-1"></div>
                </div>
            </nav>
            <div id="product-content-container">
                <div id="product-content-info-wrapper">
                    <div id="product-content-info">
                        <c:set var="numberProduct" value="${numberAllPage}"></c:set>
                        <h5 id="numberProduct">${numberProduct} Products</h5>                      
                        <div id="pagination">
                            <div id="showCurrentPage">Page 1 of ${allPage}</div> 
                            <i id="back-page-btn" class="bi bi-chevron-left disable-icon" ></i>
                            <c:if test="${allPage==1}">
                                <i id="next-page-btn" class="bi bi-chevron-right disable-icon"></i>
                            </c:if>
                            <c:if test="${allPage!=1}">
                                <i id="next-page-btn" class="bi bi-chevron-right" onclick="performFilter('next-page')"></i>
                            </c:if>  
                        </div>
                    </div>
                </div>
                <div id="product-content">
                    <table class="table table-bordered" id="table">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th >Staff</th>
                                <th >Date</th>
                                <th >Quantity</th>
                                <th >All <i class="bi bi-chevron-down"></i></th>
                            </tr>
                        </thead>
                        <tbody id="product-tbody">
                            <c:forEach var="productHistory" items="${phList}">
                                <c:set var="isCake" value="${productHistory.cake!=null?true:false}"></c:set>
                                    <tr>
                                        <td>
                                            <div class="product-content">                                    
                                                <div class="product-info">
                                                    <div class="product-name-id">
                                                        <div>${isCake?"Cake":"Topping"}</div>
                                                    <div>#${isCake?productHistory.cake.cakeID:productHistory.topping.toppingID}</div>
                                                </div>
                                                <div>${isCake?productHistory.cake.cakeName:productHistory.topping.toppingName}</div>
                                            </div>
                                            <div><img class="product-image" src="../../${isCake?productHistory.cake.cakeImg:productHistory.topping.toppingImg}" alt="${isCake?productHistory.cake.cakeName:productHistory.topping.toppingName}"/></div>
                                        </div>
                                    </td>
                                    <td >
                                        <div class="staff-content">                                   
                                            <div class="staff-info">
                                                <div>#${productHistory.updateBy!=null?productHistory.updateBy.staffID:productHistory.createBy.staffID}</div>
                                                <div>${productHistory.updateBy!=null?productHistory.updateBy.fullname:productHistory.createBy.fullname}</div>
                                            </div>
                                            <div><img class="staff-image" src="../../${productHistory.updateBy!=null?productHistory.updateBy.staffAvatar:productHistory.createBy.staffAvatar}" alt="alt"/></div>
                                        </div>
                                    </td>
                                    <td class="product-update-date">
                                        ${productHistory.updatedDate!=null?productHistory.updatedDate:productHistory.createDate}
                                    </td>
                                    <td class="product-update-quantity">
                                        ${productHistory.phQuantity}
                                    </td>
                                    <td  class="product-update-status">
                                        ${productHistory.updatedDate!=null?"Update":"Create"}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>

        <script src="../../assets/javascript/product-history.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>