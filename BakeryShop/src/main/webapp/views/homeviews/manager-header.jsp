<%-- 
    Document   : customer-header
    Created on : Feb 20, 2024, 9:17:13 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>


<div id="header" class="row">
    <div class="row col-sm-5">
        <div class="col-sm-5" id="catalog-container">
            <a id="catalog-link" href="/catalog">Catalog</a>
        </div>
        <div class="col-sm-7">
            <form action="searchcake" method="get" id="search-cake">
                <input type="text" name="cakeSearch" id="search-cake-input" placeholder="Search Cake">
                <button type="submit" id="search-cake-btn"><i class="bi bi-search"></i></button>
            </form>
        </div>
    </div>
    <div class="col-sm-2">
        <a href="/home" id="backhome-link">
            <h3 id="bakery-name">ELEGANTO</h3>
        </a>
    </div>
    <div class="col-sm-5 row" >
        <div class="nav-item dropdown col-sm-4" id="dropdown-container">
            <a class="nav-link dropdown-toggle" id="dropdown-management" href="#" role="button"
               data-toggle="dropdown" aria-expanded="false">
                Management
            </a>
            <div class="dropdown-menu" id="dropdown-management-list">
                <a class="dropdown-item" href="MyConfirmedOrders">Order</a>
                <a class="dropdown-item" href="ToppingManagement">Topping</a>
                <a class="dropdown-item" href="CakeManagement">Cake</a>
                <a class="dropdown-item" href="rating-management">Rating</a>
                <a class="dropdown-item" href="staff-management">Staff</a>
                <a class="dropdown-item" href="product-history">Product History</a>
            </div>
        </div>
           <div class="nav-item dropdown col-sm-4" id="dropdown-container">
            <a class="nav-link dropdown-toggle" id="dropdown-management" href="#" role="button"
               data-toggle="dropdown" aria-expanded="false">
                Statistic
            </a>
            <div class="dropdown-menu" id="dropdown-management-list">
                <a class="dropdown-item" href="views/adminviews/order-statistic-page.jsp">Order</a>
                <a class="dropdown-item" href="views/adminviews/topping-statistic-page.jsp">Topping</a>
                <a class="dropdown-item" href="views/adminviews/cake-statistic-page.jsp">Cake</a>
                <a class="dropdown-item" href="views/adminviews/rating-statistics-page.jsp">Rating</a>
                <a class="dropdown-item" href="views/adminviews/staff-statistic-page.jsp">Staff</a>
                <a class="dropdown-item" href="views/adminviews/product-history-statistic-page.jsp">Product History</a>
            </div>
        </div>
        <div  class="col-sm-4">
            <ul style="max-width: 100%" id="navbar-icon">
                <li class="nav-item dropdown" id="dropdown-container">
                    <a class="nav-link dropdown-toggle" id="dropdown-management" href="#" role="button"
                       data-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-person-circle h4">
                        </i>
                    </a>
                    <div class="dropdown-menu" id="dropdown-management-list">
                        <a class="dropdown-item" href="editProfile">Edit profile</a>
                        <a class="dropdown-item" href="changePassword">Change password</a>
                        <a class="dropdown-item" href="logout">Log out</a>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</div>
