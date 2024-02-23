<%-- 
    Document   : purchase-history
    Created on : Feb 22, 2024, 11:28:03 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%@include file="../homeviews/staff-header.jsp" %>
        <div id="content">
            <div id="url">
                    <a id="home-link" href="#">Home</a>
                    <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                    <span id="title">Purchase History</span>
                </div>
            <nav id="nav-bar-container">
                <ul id="nav-bar" class="row">
                    <li class="col-sm-1" id="nav-bar--active"><button disabled class="nav-bar-items">All</button></li>
                    <li class="col-sm-2"><button class="nav-bar-items">Was Paid</button></li>
                    <li class="col-sm-2"><button class="nav-bar-items">Waiting</button></li>
                    <li class="col-sm-2"><button class="nav-bar-items">Accepted</button></li>
                    <li class="col-sm-2"><button class="nav-bar-items">Completed</button></li>
                    <li class="col-sm-3"></li>
                </ul>
            </nav>
            <div id="filter-container">
                <div class="row">
                <form id="search-order-form" class="col-sm-4">
                <input type="text" placeholder="Search"/>
                <button type="submit"><i class="bi bi-search"></i></button>
                </form>
                    <div class="col-sm-2">
                        
                    </div>
                <div class="col-sm-3 date-container" >
                    <form>
                        <label for="order-date">Order Date</label>
                        <input type="date" id="order-date">
                    </form>
                </div>
                <div class="col-sm-3 date-container">
                    <form>
                        <label for="order-receive">Receive Date</label>
                    <input type="date" id="order-receive">
                    </form>
                </div>
                </div>
            </div>
            <div id="order-content-container" >
                <div id="all-order-info">
                <h5 id="number-order">42 Orders</h5>
                <div id="page-info">
                    Page 1 of 2
                    <i id="back-page-btn" class="bi bi-chevron-left disable-icon"></i>
                    <i id="next-page-btn" class="bi bi-chevron-right"></i>
                </div>
                </div>
                <div class="order-content row">
                    <div class="col-sm-7 row order-content-total-info">
                     <div class="order-id col-sm-2">#243242</div>
                    <div class="order-content-date col-sm-2"><i class="bi bi-cart"></i> 20/2/2024</div>
                    <div class="order-receive-date col-sm-2"><i class="bi bi-truck"></i> None</div>
                    <div class="order-total-price col-sm-4">Total Price: 1.450.000đ</div>
                    </div>                   
                    <div class="col-sm-1"></div>
                    <div class="col-sm-4 row order-content-status-info">
                     <div class="order-staus col-sm-6">Status: Waiting</div>
                    <div class="order-staffID col-sm-6">Staff <i class="bi bi-person-check"></i> #11</div>
                    </div>
                    
                </div>
                <div class="orderdDetail-content row">
                    <div class="orderDetail-card orderDetail-card--unshowTopping col-sm-3">
                        <div class="orderDetail-cakeName">Chocolate Cake</div>
                        <div class="cake-info row">                          
                            <img class="orderDetail-cakeImage col-sm-6" src="./../../Image/Cake/chocolate_cake.jpg"/>
                            <div class="col-sm-6">
                                <div class="orderDetail-cakePrice">Price: 300.000đ</div>
                                <div class="orderDetail-cakeQuantity">Quantity: 2</div>
                                <div class="showTopping-btn-cotainer">                                    
                                <button class="showTopping-btn">Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>                           
                                </div>
                            </div>
                        </div>
                        <div class="orderDetail-totalPrice">Total Price: 830.000đ</div>
                    </div>
                    <div class="orderDetail-card orderDetail-card--haveTopping col-sm-3">
                        <div class="orderDetail-cakeName">Chocolate Cake</div>
                        <div class="cake-info row">                          
                            <img class="orderDetail-cakeImage col-sm-6" src="./../../Image/Cake/chocolate_cake.jpg"/>
                            <div class="col-sm-6">
                                <div class="orderDetail-cakePrice">Price: 300.000đ</div>
                                <div class="orderDetail-cakeQuantity">Quantity: 2</div>
                                <div class="showTopping-btn-cotainer">                                    
                                <button class="showLess-btn">Show Less <i class="bi bi-chevron-double-up"></i></button>                           
                                </div>
                            </div>    
                             <div class="toppingInfo col-sm-12">
                                <div>Strawberry:
                                    <div class="toppingDetail">
                                        <div class="toppingDetail-price">Price: 20.000đ</div>
                                        <div class="toppingDetail-quantity">Quantity: 3</div>
                                    </div>
                                </div>
                                <div>Raisins:
                                    <div class="toppingDetail">
                                        <div class="toppingDetail-price">Price: 30.000đ</div>
                                        <div class="toppingDetail-quantity">Quantity: 2</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="orderDetail-totalPrice">Total Price: 830.000đ</div>
                    </div> 
                    
                    <div class="col-sm-12 showMore-btn-cotainer">                      
                    <button class="showMore-btn ">Show More <i id class="bi bi-chevron-double-down down-icon"></i></button>
                    </div>
                    </div>
                </div>
            </div>
        </div>         
                <script  src="../../assets/javascript/view-rating.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>
</html>
