<%-- 
    Document   : staff-statistic-page
    Created on : Mar 6, 2024, 12:34:25 PM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@page import="daos.StaffDAO"%>
<%@page import="models.Staff"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics Page</title>
        <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../../assets/css/statistics.css"/>
    </head>
    <body>
        <div class="container-fluid position-relative d-flex p-0 row">
            <div class="sidebar pe-4 pb-3 col-sm-3">
                <nav class="navbar bg-secondary navbar-white">
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <%
                            User user = (User) session.getAttribute("user");
                            StaffDAO sd = new StaffDAO();
                            Staff manager = (Staff) sd.findByID(user.getId());
                            String staffAvatar = manager.getStaffAvatar();
                            String fullname = manager.getFullname();
                        %>
                        <div class="position-relative">
                            <img class="rounded-circle" src="../../<%= staffAvatar%>" alt="" style="width: 40px; height: 40px;">
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0"><%=fullname%></h6>
                            <span>Admin</span>
                        </div>
                    </div>
                    <div class="navbar-nav w-100">
                        <a href="../../home" class="nav-item nav-link" ><i class="bi bi-house"></i>Home</a>
                        <a href="#" class="nav-item nav-link active"><i class="bi bi-bag-check-fill"></i>Order</a>
                        <a href="../../views/adminviews/topping-statistic-page.jsp" class="nav-item nav-link"><i class="bi bi-patch-plus"></i> Topping</a>
                        <a href="../../views/adminviews/cake-statistic-page.jsp" class="nav-item nav-link"><i class="bi bi-cake-fill"></i> Cake</a>
                        <a href="../../views/adminviews/rating-statistics-page.jsp" class="nav-item nav-link"><i class="bi bi-star-half"></i> Rating</a>
                        <a href="../../views/adminviews/staff-statistic-page.jsp" class="nav-item nav-link "><i class="bi bi-file-person-fill"></i> Staff</a>
                        <a href="../../views/adminviews/product-history-statistic-page.jsp" class="nav-item nav-link"><i class="bi bi-backpack2-fill"></i> Product History</a>                                             
                        <a href="../../MyConfirmedOrders" class="nav-item nav-link"><i class="bi bi-skip-backward-fill"></i> Back</a>
                    </div>
                </nav>
            </div>
            <div class="content col-sm-9">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12 col-xl-6" id="all-chart">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h4 class="chart-title">Order status every week</h4>
                                <div class="option-dropdown">
                                    <div class="dropdown" id="pie-option-dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" id="pie-option-chart-btn" type="button" data-toggle="dropdown" aria-expanded="false">
                                            Status
                                        </button>
                                        <div class="dropdown-menu" id="pie-option-chart">
                                            <a class="dropdown-item active-weak-line" href="#">Status</a>
                                            <a class="dropdown-item" href="#">Was Paid</a>
                                        </div>
                                    </div>
                                    <div class="dropdown" id="weeks-pie-dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" id="week-pie-dropdown-btn" type="button" data-toggle="dropdown" aria-expanded="false">
                                            Weeks
                                        </button>
                                        <div class="dropdown-menu" id="week-pie-dropdown">

                                        </div>
                                    </div>
                                </div>
                                <canvas id="pie-chart"></canvas>                               
                            </div>
                        </div>
                        <div class="col-sm-12 col-xl-6">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h4 class="chart-title">Orders every week</h4>
                                <div class="option-dropdown">
                                    <div class="dropdown" id="order-option-dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" id="order-option-list-chart-btn" type="button" data-toggle="dropdown" aria-expanded="false">
                                            Total Income
                                        </button>
                                        <div class="dropdown-menu" id="order-option-list-chart">
                                            <a class="dropdown-item active-weak-line" href="#">Total Income</a>
                                            <a class="dropdown-item" href="#">Number of Orders</a>
                                        </div>
                                    </div>
                                    <div class="dropdown" id="weeks-order-dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" id="week-option-dropdown-btn" type="button" data-toggle="dropdown" aria-expanded="false">
                                            Weeks
                                        </button>
                                        <div class="dropdown-menu" id="week-option-dropdown">

                                        </div>
                                    </div>
                                </div>
                                <canvas id="line-chart"></canvas>
                                <div id="summarize-content-order">
                                    <h4 class="mb-4 chart-title" id="order-summarize-title">Summarize Information</h4>  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="../../assets/javascript/chart.min.js"></script>
            <script src="../../assets/javascript/waypoints.min.js"></script>
            <script src="../../assets/javascript/owl.carousel.min.js"></script>
            <script src="../../assets/javascript/moment.min.js"></script>
            <script src="../../assets/javascript/tempusdominus-bootstrap-4.min.js"></script>
            <script src="../../assets/javascript/order-data-chart.js"></script>
    </body>
</html>

