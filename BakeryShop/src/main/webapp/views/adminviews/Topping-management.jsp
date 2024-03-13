<%-- 
    Document   : Topping-management
    Created on : Mar 1, 2024, 1:43:02 PM
    Author     : ACER PC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/my-confirmed-orders.css"/>
        <link rel="stylesheet" href="../../assets/css/topping-management.css"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../homeviews/Header.jsp" %>
        <div class="content container-fluid">
            <div class="top-option">
                <div class="menu-and-history row">
                    <div class="histoty col-sm-4 row">
                        <h4 class="Home-text"><a class="pre-page" href="/home">Home</a></h4>
                        <h4>&gt</h4>
                        <h4 class="current-page">Topping Management</h4>
                    </div>
                    <div class="col-sm-4"></div>

                </div>
                <!--                <form id="searchForm">
                                    <label for="searchTerm">Search Order:</label>
                                    <input type="text" id="searchTerm" name="searchTerm">
                                    <button type="button" onclick="searchOrders()">Search</button>
                                </form>-->
                <div class="search-and-filter d-flex align-items-center row">
                    <div id="searchForm" class="searchbar col-sm-4">
                        <form class="search-order" action="ToppingManagement" method="Post">
                            <input
                                type="text"
                                class="search-order-input"
                                placeholder="Search Topping"
                                id="searchTerm"
                                name ="searchTerm"
                                />
                            <button type="submit" class="search-order-btn">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                    <div class="col-sm-5"></div>
                    <div class="col-sm-3 d-flex align-items-center justify-content-around">
                        <c:if test="${sessionScope.user.role eq 'manager'}">
                            <div id="statistic-link-wrapper">                       
                                <a href="../../views/adminviews/topping-statistic-page.jsp" id="statistic-link">Statistics <i class="bi bi-bar-chart"></i></a>
                            </div>
                        </c:if>
                        <button onclick="redirectToAddToppingPage()" id="add-topping-btn" >Add Topping<i class="add-icon bi bi-plus"></i></button>
                    </div>
                    <!--                    <div class="swap-page col-sm-3 d-flex align-items-center justify-content-end">
                                            <p class="swap-page-item page-number">Page <span id="currentPage">1</span> of <span id="totalPages">1</span></p>
                                            <button class="swap-page-item prev-page swap-page-button">&lt</button>
                                            <button class="swap-page-item next-page swap-page-button">&gt</button>
                                        </div>-->
                </div>
            </div>
            <div class="content-topping">

                <div class="topping-list row" id="toppingList">
                    <c:forEach var="topping" items="${toppingList}">
                        
                        <div class="col-sm-3 topping-in-order-cover" onclick="redirectToToppingDetailPage(${topping.toppingID})" style="padding: 0">
                            <div class="topping-detai">
                                <p class="topping-id">#<c:out value="${topping.toppingID}" /></p>
                                <div class="d-flex align-items-center justify-content-center">
                                    <p class="topping-name"><c:out value="${topping.toppingName}" /></p>
                                </div>
                                <div class="row">
                                    <img class="topping-img" src="${topping.toppingImg}" />
                                    <div class="col-sm-6 topping-right-inf">
                                        <!-- Thêm các dòng khác theo định dạng tương tự -->
                                        <div class="d-flex justify-content-start align-items-center">
                                            <p class="topping-inf-title">Price: </p>
                                            <p class="topping-inf"> <fmt:formatNumber value="${topping.toppingPrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</p>
                                        </div>
                                        <div class="d-flex justify-content-start align-items-center">
                                            <p class="topping-inf-title">Quantity: </p>
                                            <p class="topping-inf">${topping.toppingQuantity}</p>
                                            <!--                            <button class=" plus-btn d-flex align-items-center"><i class="bi bi-plus-square-fill"></i></button>-->
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <p class="topping-inf-title">Description: </p>
                                    <p class="topping-inf topping-description"><c:out value="${topping.toppingDescription}" /></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-sm-12 d-flex justify-content-center" id="empty-notice" style="display: ${isEmpty ? 'flex' : 'none'}!important">
                        <h2 class="text-danger">No products were found!!!</h2>
                    </div>
            </div>
        </div>
        <script src="../../assets/javascript/topping-management.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"
        ></script>

    </body>
</html>