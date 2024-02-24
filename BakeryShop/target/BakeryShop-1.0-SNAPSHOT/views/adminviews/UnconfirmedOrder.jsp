<%-- 
    Document   : UnconfirmedOrder
    Created on : Feb 23, 2024, 8:41:05 PM
    Author     : ACER PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/view-rating.css">
        <link rel="stylesheet" href="../../assets/css/my-confirmed-orders.css"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../homeviews/staff-header.jsp" %>
        <div class="content container-fluid">
            <div class="top-option">
                <div class="menu-and-history row">
                    <div class="histoty col-sm-4 row">
                        <h4 class="Home-text">Home</h4>
                        <h4>&gt</h4>
                        <h4 class="current-page">Unconfirmed Orders</h4>
                    </div>
                    <div class="col-sm-4"></div>
                    <div class="menu col-sm-4 row">
                        <button class="menu-item my-confirmed-order-btn menu-default col-sm-5">
                            My Confirmed Orders
                        </button>
                        <div class="col-sm-2"></div>
                        <button class="menu-item unconfirmed-order-btn menu-variant col-sm-5">
                            Unconfirmed Orders
                        </button>
                    </div>
                </div>
                <div class="search-and-filter d-flex align-items-center row">
                    <div class="searchbar col-sm-4">
                        <form class="search-order" action="UnconfirmedOrders" method="get">
                            <input
                                type="text"
                                name="searchTerm"
                                class="search-order-input"
                                placeholder="Search Order"
                                />
                            <button type="submit" class="search-order-btn">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                    <div class="col-sm-2"></div>
                    <div class="col-sm-3"></div>
                    <div class="swap-page col-sm-3 d-flex align-items-center justify-content-end">
                        <p class="swap-page-item page-number">Page <span id="currentPage">1</span> of <span id="totalPages">1</span></p>
                        <button class="swap-page-item prev-page swap-page-button">&lt</button>
                        <button class="swap-page-item next-page swap-page-button">&gt</button>
                    </div>
                </div>
            </div>
            <div class="Order-list row container-fluid" id="orderList">

                <c:forEach var="order" items="${myOrderList}" > 
                    <div class="col-sm-3">
                        <div class="Order-card">
                            <div class="Order-title d-flex align-items-center justify-content-center">
                                <p class="Order-id">Order ID: #${order.orderID}</p>
                            </div>
                            <div class="Order-content d-flex align-items-center justify-content-start">
                                <p class="Order-content-text">Order Date: ${order.orderDate}</p>
                            </div>
                            <div class="Order-content d-flex align-items-center justify-content-start">
                                <p class="Order-content-text">Status: ${order.status}</p>
                                <div class="rounded-circle circle ${order.status eq 'Waiting' ? 'circle-blue' : (order.status eq 'Delivering' ? 'circle-yellow' : 'circle-green')}"></div>

                                <div id="statusCircle" class="rounded-circle circle"></div>

                            </div>
                            <div class="Order-content d-flex align-items-center justify-content-start">
                                <p class="Order-content-text">
                                    <c:if test="${order.wasPaid}">
                                        Already paid: Yes
                                    </c:if>
                                    <c:if test="${not order.wasPaid}">
                                        Already paid: No
                                    </c:if>
                                </p>
                                <div class="rounded-circle circle ${order.wasPaid ? 'Paid' : 'NotPaid'}"></div>

                            </div>

                            <div class="Order-total-price d-flex align-items-center justify-content-end">
                                <p class="Total-price">Total Price: ${order.totalPrice}đ </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="../../assets/javascript/unconfirmed-orders.js"></script>
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
