<%-- 
    Document   : my-comfirmed-orders
    Created on : Feb 23, 2024, 10:28:56 AM
    Author     : Nguyen Truong An
--%>

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
        <div id="header" class="row">
            <div class="row col-sm-5">
                <div class="col-sm-5" id="catalog-container">
                    <a id="catalog-link" href="#">Catalog</a>
                </div>
                <div class="col-sm-7">
                    <form id="search-cake">
                        <input type="text" name="search-cake-input" id="search-cake-input" placeholder="Search Cake">
                        <button type="submit" id="search-cake-btn"><i class="bi bi-search"></i></button>
                    </form>
                </div>
            </div>
            <div class="col-sm-2">
                <a href="#" id="backhome-link">
                    <h3 id="bakery-name">ELEGANTO</h3>
                </a>
            </div>
            <div class="col-sm-5 row">
                <div class="nav-item dropdown col-sm-6" id="dropdown-container">
                    <a class="nav-link dropdown-toggle" id="dropdown-management" href="#" role="button"
                       data-toggle="dropdown" aria-expanded="false">
                        Management
                    </a>
                    <div class="dropdown-menu" id="dropdown-management-list">
                        <a class="dropdown-item" href="#">Order</a>
                        <a class="dropdown-item" href="#">Topping</a>
                        <a class="dropdown-item" href="#">Cake</a>
                        <a class="dropdown-item" href="#">Rating</a>
                    </div>
                </div>
                <ul id="navbar-icon" class="col-sm-6">
                    <li><a href="#"><i class="bi bi-basket3"></i></a></li>
                    <li><a href="#"><i class="bi bi-clock-history"></i></a></li>
                    <li><a href=""><i class="bi bi-person-circle"></i></a></li>
                </ul>
            </div>
        </div>
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
                    <button class="menu-item menu-variant col-sm-5">
                        My Confirmed Orders
                    </button>
                    <div class="col-sm-2"></div>
                    <button class="menu-item menu-default col-sm-5">
                        Unconfirmed Orders
                    </button>
                </div>
            </div>
            <div class="search-and-filter d-flex align-items-center row">
                <div class="searchbar col-sm-4">
                    <form class="search-order">
                        <input
                            type="text"
                            name="s earch-cake-input"
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
                    <p class="swap-page-item page-number">Page 1 of 2</p>
                    <button class="swap-page-item swap-page-button">&lt</button>
                    <button class="swap-page-item swap-page-button">&gt</button>
                </div>
            </div>
        </div>
            <div class="Order-list row container-fluid">
                <div class="col-sm-3">
                <div class="Order-card">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #001</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: 25/01/2024</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: Accepted</p>
                        <div class="rounded-circle circle circle-yellow"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Already paid: Yes</p>
                        <div class="rounded-circle circle circle-green"></div>
                    </div>
                    
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: 830.000đ </p>
                    </div>
                </div>
            </div>
                <div class="col-sm-3">
                <div class="Order-card">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #001</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: 25/01/2024</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: Accepted</p>
                        <div class="rounded-circle circle circle-yellow"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Already paid: Yes</p>
                        <div class="rounded-circle circle circle-green"></div>
                    </div>
                    
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: 830.000đ </p>
                    </div>
                </div>
            </div>
                <div class="col-sm-3">
                <div class="Order-card">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #001</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: 25/01/2024</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: Accepted</p>
                        <div class="rounded-circle circle circle-yellow"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Already paid: Yes</p>
                        <div class="rounded-circle circle circle-green"></div>
                    </div>
                    
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: 830.000đ </p>
                    </div>
                </div>
            </div>
                <div class="col-sm-3">
                <div class="Order-card">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #001</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: 25/01/2024</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: Accepted</p>
                        <div class="rounded-circle circle circle-yellow"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Already paid: Yes</p>
                        <div class="rounded-circle circle circle-green"></div>
                    </div>
                    
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: 830.000đ </p>
                    </div>
                </div>
            </div>
                <div class="col-sm-3">
                <div class="Order-card">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #001</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: 25/01/2024</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: Accepted</p>
                        <div class="rounded-circle circle circle-yellow"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Already paid: Yes</p>
                        <div class="rounded-circle circle circle-green"></div>
                    </div>
                    
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: 830.000đ </p>
                    </div>
                </div>
            </div>
            </div>
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
