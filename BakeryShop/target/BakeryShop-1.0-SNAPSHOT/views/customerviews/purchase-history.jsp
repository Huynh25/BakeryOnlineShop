<%-- 
    Document   : purchase-history
    Created on : Feb 22, 2024, 11:28:03 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase History</title>  <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/purchase-history.css">
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
                <a id="home-link" href="/home">Home</a>
                <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                <span id="title">Purchase History</span>
            </div>
            <nav id="nav-bar-container">
                <ul id="nav-bar" class="row">
                    <li class="col-sm-1 nav-bar--active" id="all-filter"><button disabled class="nav-bar-items" onclick="performFilter('all')">All</button></li>
                    <li class="col-sm-2" id="wasPaid-filter"><button class="nav-bar-items"  onclick="performFilter('wasPaid')">Was Paid</button></li>
                    <li class="col-sm-2" id="waiting-filter"><button class="nav-bar-items"  onclick="performFilter('waiting')">Waiting</button></li>
                    <li class="col-sm-2" id="accepted-filter"><button class="nav-bar-items"  onclick="performFilter('accepted')">Accepted</button></li>
                    <li class="col-sm-2" id="completed-filter"><button class="nav-bar-items"  onclick="performFilter('completed')">Completed</button></li>
                    <li class="col-sm-3"></li>
                </ul>
            </nav>
            <div id="filter-container">
                <div class="row">
                    <form id="search-order-form" onsubmit="return false;" class="col-sm-4">
                        <input id="search-order-input" type="text" placeholder="Search"/>
                        <button onclick="performFilter('search')"><i class="bi bi-search"></i></button>
                    </form>
                    <div class="col-sm-2">

                    </div>
                    <div class="col-sm-3 date-container" >
                        <form>
                            <label for="order-date">Order Date</label>
                            <input type="date" id="order-date" onchange="performFilter('order-date')">
                        </form>
                    </div>
                    <div class="col-sm-3 date-container">
                        <form>
                            <label for="order-receive">Receive Date</label>
                            <input type="date" id="order-receive" onchange="performFilter('order-receive')">
                        </form>
                    </div>
                </div>
            </div>
            <div id="order-content-container" >
                <div id="all-order-info">                 
                    <h5 id="number-order">${numberCurrentOrder} Orders</h5>
                    <div id="page-info">
                        Page ${currentPage} of ${totalNumberPage}
                        <i id="back-page-btn"  class="bi bi-chevron-left ${currentPage==1?"disable-icon":""}" ></i>
                        <c:if test="${currentPage==totalNumberPage}">

                            <i id="next-page-btn" disabled  class="bi bi-chevron-right disable-icon"></i>
                        </c:if>
                        <c:if test="${currentPage!=totalNumberPage}">
                            <i id="next-page-btn" onclick="performFilter('Next-page')"  class="bi bi-chevron-right"></i>
                        </c:if>
                    </div>
                </div>
                <c:set var="cakeCardIndex" value="0"/>
                <c:forEach var="order" items="${orderList}" varStatus="loop">   
                    <div class="order-content row" id="order-content-${order.orderID}">
                        <div class="col-sm-7 row order-content-total-info">
                            <div class="order-id col-sm-2">#${order.orderID}</div>
                            <div class="order-content-date col-sm-3"><i class="bi bi-cart"></i> 
                                <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy" />
                            </div>

                            <div class="order-receive-date col-sm-3"><i class="bi bi-truck"></i> 
                                <c:choose>
                                    <c:when test="${order.receivedDate == null}">
                                        Undefined
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${order.receivedDate}" pattern="dd-MM-yyyy" />
                                    </c:otherwise>
                                </c:choose>
                            </div>        
                            <div class="order-total-price col-sm-4">Total Price: <fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</div>
                        </div>                   
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4 row order-content-status-info">
                            <div class="order-staus col-sm-6">Status: ${order.status}</div>
                            <div class="order-staffID col-sm-6">Staff <i class="bi bi-person-check"></i> #${order.staffID==0?'Undefined':order.staffID}</div>
                        </div>

                    </div>
                    <div class="orderdDetail-content row" id="orderdDetail-content-${order.orderID}">                                                                  
                        <div class="col-sm-6 received-btn" ${order.status == "Delivering"?"":"hidden"}>
                            <button onclick="receivedOrder(${order.orderID})">Received</button></div>
                        <div class="${order.status == "Delivering"?"col-sm-6":"col-sm-12"} col-sm-6 order-Description">
                            ${order.orderDescription} 
                        </div>                     
                        <c:set var="orderDetailQuantity" value="0" />
                        <c:forEach var="cakeInOrder" items="${cakeInOrder.get(loop.index)}"> 
                            <c:set var="ratingVlaue" value="${ratingInCake[loop.index][orderDetailQuantity].ratingValue}"></c:set>
                            <c:if test="${orderDetailQuantity <3}">
                                <div class="orderDetail-card orderDetail-card--unshowTopping col-sm-3" id="orderDetail-card-${cakeInOrder.cioID}">
                                    <div class="orderDetail-cakeName">${cakeInOrder.cake.cakeName}</div>
                                    <div class="cake-info row">                          
                                        <img class="orderDetail-cakeImage col-sm-6" src="./../../${cakeInOrder.cake.cakeImg}"/>
                                        <div class="col-sm-6 row card-info">
                                            <div class="orderDetail-cakePrice col-sm-12">Price:
                                                <fmt:formatNumber value="${cakeInOrder.cake.cakePrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                                            </div>
                                            <div class="orderDetail-cakeQuantity col-sm-12">Quantity: ${cakeInOrder.cioQuantity}</div>
                                            <div class="rate col-sm-12 cake-index-${cakeInOrder.cake.cakeID} index-array-${loop.index}-${orderDetailQuantity}" id="${cakeCardIndex}">
                                                <input type="radio" id="star5-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="5" ${ratingVlaue==5?"checked":""} />
                                                <label for="star5-${cakeCardIndex}" title="text">5 stars</label>
                                                <input type="radio" id="star4-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="4" ${ratingVlaue==4?"checked":""}/>
                                                <label for="star4-${cakeCardIndex}" title="text">4 stars</label>
                                                <input type="radio" id="star3-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="3" ${ratingVlaue==3?"checked":""}/>
                                                <label for="star3-${cakeCardIndex}" title="text">3 stars</label>
                                                <input type="radio" id="star2-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="2" ${ratingVlaue==2?"checked":""}/>
                                                <label for="star2-${cakeCardIndex}" title="text">2 stars</label>
                                                <input type="radio" id="star1-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="1" ${ratingVlaue==1?"checked":""}/>
                                                <label for="star1-${cakeCardIndex}" title="text">1 stars</label>                   
                                            </div>
                                            <div class="showTopping-btn-cotainer col-sm-12">                                    
                                                <button class="showTopping-btn" onclick="ShowTopping(${cakeInOrder.cioID},${cakeInOrder.cioQuantity * cakeInOrder.cake.cakePrice})">Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>                           
                                            </div>
                                        </div>
                                    </div>
                                    <div class="orderDetail-totalPrice">Total Cake Price: 
                                        <fmt:formatNumber value="${cakeInOrder.cioQuantity * cakeInOrder.cake.cakePrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ
                                    </div>                                
                                </div>
                                <c:set var="orderDetailQuantity" value="${orderDetailQuantity+1}" /> 
                                <c:set var="cakeCardIndex" value="${cakeCardIndex + 1}" />
                            </c:if>
                        </c:forEach>
                        <c:if test="${orderDetailQuantity > 3}">                          
                            <div class="col-sm-12 showMore-btn-cotainer">                      
                                <button class="showMore-btn " onclick="showMore(${order.orderID}, 3)">Show More <i id class="bi bi-chevron-double-down down-icon"></i></button>
                            </div>
                        </c:if>



                    </div>
                </c:forEach>
            </div>
        </div>

    </div>   
    <!--Cart popup-->
    <%@include file="../homeviews/Cart.jsp" %>

    <div id="ratingPopup" class="popup">
        <div class="popup-content">
            <h3>Enter your comment</h3>
            <textarea id="comment" cols="50" rows="5"></textarea>
            <button class="yes-option" onclick="handleRating()">Yes</button>
            <button class="no-option" onclick="ClosePopup()">No</button>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>
    <script  src="../../assets/javascript/purchase-history.js"></script>
</body>
</html>
