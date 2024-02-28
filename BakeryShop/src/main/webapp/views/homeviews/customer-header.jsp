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
            <form action="/searchcake" method="get" id="search-cake">
                <input type="text" name="cakeSearch" id="search-cake-input" placeholder="Search Cake">
                <button type="submit" id="search-cake-btn"><i class="bi bi-search"></i></button>
            </form>
        </div>
    </div>
    <div class="col-sm-4">
        <a href="/home" id="backhome-link">
            <h3 id="bakery-name">ELEGANTO</h3>
        </a>
    </div>
    <div class="col-sm-3">
        <ul id="navbar-icon">
            <li><a href="#"><i class="bi bi-basket3"></i></a></li>
            <li><a href="order-history"><i class="bi bi-clock-history"></i></a></li>
            <li><a href=""><i class="bi bi-person-circle"></i></a></li>
        </ul>
    </div>
</div>
