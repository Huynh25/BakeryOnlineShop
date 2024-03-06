<%-- 
    Document   : Cake-management
    Created on : Mar 1, 2024, 1:43:02 PM
    Author     : ACER PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/my-confirmed-orders.css"/>
        <link rel="stylesheet" href="../../assets/css/cake-management.css"/>
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
                        <h4 class="current-page">My Confirmed Orders</h4>
                    </div>
                    <div class="col-sm-4"></div>
                    <div class="menu col-sm-4 row">
                        <button class="menu-item my-confirmed-order-btn menu-variant col-sm-5">
                            My Confirmed Orders
                        </button>
                        <div class="col-sm-2"></div>
                        <button class="menu-item unconfirmed-order-btn menu-default col-sm-5">
                            Unconfirmed Orders
                        </button>
                    </div>
                </div>
                <!--                <form id="searchForm">
                                    <label for="searchTerm">Search Order:</label>
                                    <input type="text" id="searchTerm" name="searchTerm">
                                    <button type="button" onclick="searchOrders()">Search</button>
                                </form>-->
                <div class="search-and-filter d-flex align-items-center row">
                    <div id="searchForm" class="searchbar col-sm-4">
                        <form class="search-order" action="MyConfirmedOrders" method="get">
                            <input
                                type="text"
                                class="search-order-input"
                                placeholder="Search Order"
                                id="searchTerm"
                                name ="searchTerm"
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
            <div class="content-cake">
                <div class=" container-fluid d-flex justify-content-end">
                    <div class=" type-filter dropdown d-flex">
                        <p class="mr-2">Type:</p>
                        <button class="btn btn-filter dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            All
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="dropdownItems">
                        </div>
                    </div>
                </div>
            <div class="cake-list row" id="cakeList">
                <div class="col-sm-4 cake-in-order-cover">
                    <div class="cake-detai">
                        <p class="cake-id">#01</p>
                        <div class="d-flex align-items-center justify-content-center">
                            <p class="cake-name">Strawberry Cake</p>
                        </div>
                        <div class="row">
                        <img class="cake-img" src="../../Image/Cake/strawberry_cake.jpg" alt="alt"/>
                        <div class="col-sm-6">
                        <div class="d-flex justify-content-start align-items-center">
                            <p class="cake-inf-title">Price: </p>
                            <p class="cake-inf">250.000đ</p>
                        </div>
                        <div class="d-flex justify-content-center align-items-center">
                            <p class="cake-inf-title">Quantity: </p>
                            <p class="cake-inf">2</p>
                            <button class=" plus-btn d-flex align-items-center"><i class="bi bi-plus-square-fill"></i></button>
                        </div>
                             <div class="d-flex justify-content-center align-items-center">
                            <p class="cake-inf-title">Type: </p>
                            <p class="cake-inf">Sponge cake</p>
                        </div>
                        </div>
                        </div>
                        <div>
                            <p class="cake-inf-title">Description: </p>
                            <p class="cake-inf">Sponge Delight: Taste the delightful simplicity of our sponge cake</p>
                        </div>
                    </div>
                </div>  
                <div class="col-sm-4 cake-in-order-cover">
                    <div class="cake-detai">
                        <div>
                        <p class="cake-id">#01</p>
                        </div>
                    </div>
                </div>   
                <div class="col-sm-4 cake-in-order-cover">
                    <div class="cake-detai">

                    </div>
                </div>  

            </div>
        </div>
        <script src="../../assets/javascript/cake-management.js"></script>
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