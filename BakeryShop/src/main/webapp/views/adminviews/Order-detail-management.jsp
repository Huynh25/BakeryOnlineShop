<%-- 
    Document   : Order-detail-management
    Created on : Feb 26, 2024, 2:46:59 PM
    Author     : ACER PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/order-detail-management.css"/>
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
        <div class="container-fluid content-top">
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
        </div>
        <div class="container-fluid">
            <div class="order-information d-flex align-items-center">
                <div class="container-fluid row">
                    <div class="col-sm-1 order-detail-inf order-inf-id">Order #36</div>
                    <div class="col-sm-2 order-detail-inf">Order Date: 22/12/2023</div>
                    <div class="col-sm-3 order-detail-inf order-inf-receivedDate">Received Date: 22/12/2023</div>
                    <div class="col-sm-2 order-detail-inf">Status: Waiting</div>
                    <div class="col-sm-2 order-detail-inf">Person in charge: #3214</div>
                    <div class="col-sm-2 order-detail-inf">Total Price: 560.000đ</div>
                </div>
            </div>
        </div>
        <div class="cake-in-order d-flex align-items-center row">
            <div class="col-sm-4 cake-in-order-cover">
                <div class="cake-in-order-detai">
                    <div class="d-flex justify-content-center">
                        <p class="cake-name">Chocolate Cake</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <img class="cake-img col-sm-5" src="../../Image/Cake/chocolate_cake.jpg"/>
                        <div class="cake-detail col-sm-5">
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Price:300.000đ</p>
                            </div>
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Quantity: 2</p>
                            </div>
                            <p class="cake-inf-text">Rating:</p>
                            <div class="d-flex justify-content-center">
                            <div class="rating-stars">
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                            </div>
                                </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center" id="show-topping-btn">
                        <button>Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>
                    </div>
                </div>  
            </div>
            <div class="col-sm-4 cake-in-order-cover">
                <div class="cake-in-order-detai">
                    <div class="d-flex justify-content-center">
                        <p class="cake-name">Chocolate Cake</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <img class="cake-img col-sm-5" src="../../Image/Cake/chocolate_cake.jpg"/>
                        <div class="cake-detail col-sm-5">
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Price:300.000đ</p>
                            </div>
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Quantity: 2</p>
                            </div>
                            <p class="cake-inf-text">Rating:</p>
                            <div class="d-flex justify-content-center">
                            <div class="rating-stars">
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                            </div>
                                </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center" id="show-topping-btn">
                        <button>Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>
                    </div>
                </div>  
            </div>
            <div class="col-sm-4 cake-in-order-cover">
                <div class="cake-in-order-detai">
                    <div class="d-flex justify-content-center">
                        <p class="cake-name">Chocolate Cake</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <img class="cake-img col-sm-5" src="../../Image/Cake/chocolate_cake.jpg"/>
                        <div class="cake-detail col-sm-5">
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Price:300.000đ</p>
                            </div>
                            <div class="d-flex justify-content-center">
                            <p class="cake-inf-text">Quantity: 2</p>
                            </div>
                            <p class="cake-inf-text">Rating:</p>
                            <div class="d-flex justify-content-center">
                            <div class="rating-stars">
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star-fill"></i>
                                <i class="bi bi-star"></i>
                                <i class="bi bi-star"></i>
                            </div>
                                </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center" id="show-topping-btn">
                        <button>Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>
                    </div>
                </div>  
            </div>
        </div>
        <div class="container btn-contain d-flex justify-content-around align-items-center">
            <button> <i class="bi bi-telephone-outbound-fill"></i>Contact Customer</button>
            <button> <i class="bi bi-truck"></i>Accept This Order</button>
        </div>

        <script src="../../assets/javascript/order-detail-management.js"></script>
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