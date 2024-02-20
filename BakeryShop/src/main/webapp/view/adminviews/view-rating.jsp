<%-- 
    Document   : view-rating
    Created on : Feb 20, 2024, 9:08:48 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
    <link rel="stylesheet" href="../../assets/css/view-rating.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <title>View Rating</title>
</head>

<body>
    <%@include file="../homeviews/staff-header.jsp" %>
        <div id="content" class="row">
        <aside id="aside-left" class="col-sm-4">
            <div id="url">
                <a id="home-link" href="#">Home</a>
                <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                <span id="title">Rating Management</span>
            </div>
            <div class="rating-cake-card rating-cake-card--detail" id="01">
                <div class="cake-card-title" onclick="toggleDetail('01')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-up up-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn">
                        <button>More Detail</button>
                    </div>
                </div>
            </div>

            <div class="rating-cake-card rating-cake-card--short" id="02">
                <div class="cake-card-title" onclick="toggleDetail('02')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-down down-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn">
                        <button>More Detail</button>
                    </div>
                </div>
            </div>

            <div class="rating-cake-card rating-cake-card--short" id="03">
                <div class="cake-card-title" onclick="toggleDetail('03')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-down down-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn">
                        <button>More Detail</button>
                    </div>
                </div>
            </div>

            <div class="rating-cake-card rating-cake-card--short" id="04">
                <div class="cake-card-title" onclick="toggleDetail('04')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-down down-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn">
                        <button>More Detail</button>
                    </div>
                </div>
            </div>

            <div class="rating-cake-card rating-cake-card--detail" id="05">
                <div class="cake-card-title" onclick="toggleDetail('05')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-up up-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn disable-icon">
                        <button disabled>More Detail</button>
                    </div>
                </div>
            </div>

            <div class="rating-cake-card rating-cake-card--short" id="06">
                <div class="cake-card-title" onclick="toggleDetail('06')">
                    <h6 class="cake-card-name">Pineapple cake</h6>
                    <div class="cake-card-icon">
                        <div class="cake-card-stars-icon">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <i id class="bi bi-chevron-double-down down-icon"></i>
                    </div>
                </div>
                <div class="cake-card-detail-container">
                    <div class="cake-card-detail">
                        <img class="cake-card-image" src="../../Image/Cake/pineapple_cake.jpg" alt="pineapple_cake">
                        <div class="cake-card-info">
                            <div class="cake-card-type-and-id">
                                <div class="cake-card-type">Sponge Cake</div>
                                <div class="cake-card-id">#01</div>
                            </div>
                            <div class="cake-card-price">
                                250.000đ
                            </div>
                            <div class="cake-card-number-rating">
                                25 Ratings
                            </div>
                            <div class="cake-card-rating-value">
                                <div class="cake-card-stars-icon">
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star-fill"></i>
                                    <i class="bi bi-star"></i>
                                </div>
                                <span class="cake-card-numberic-rating-value">4.00</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cake-card-more-detail-btn-container">
                    <div class="cake-card-more-detail-btn">
                        <button>More Detail</button>
                    </div>
                </div>
            </div>
        </aside>
        <aside id="aside-right" class="col-sm-8">
            <h3 id="rating-title">Rating of Pineapple cake</h3>
            <div class="row">
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/voldemort.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
                <div class="rating-comment-container col-sm-6">
                    <img class="rating-comment-image" src="../../Image/Avatar/darthvader.jpg" alt="voldemort">
                    <div class="rating-comment-side-right">
                        <div class="rating-comment-title">
                            <div class="rating-comment-name">Nguyễn Dân Trí</div>
                            <div class="rating-comment-date">20/2/2023</div>
                        </div>
                        <div class="cake-card-stars-icon rating-comment-value">
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star-fill"></i>
                            <i class="bi bi-star"></i>
                        </div>
                        <div class="rating-comment">
                            Bánh có vẻ đẹp và hương vị ngon, nhưng
                            không hợp với khẩu vị của mình, vẫn sẽ đánh
                            giá 4 sao vì chất lượng là không thể chối cãi.
                        </div>
                    </div>
                </div>
            </div>
            <div class="show-more-button">
                <div>Show more</div>
                <i class="bi bi-chevron-double-down down-icon"></i>
            </div>
        </aside>
    </div>
    <script src="../../assets/javascript/view-rating.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
</body>

</html>