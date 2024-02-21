let ratingDetailId = 'rating-detail-1';
let numberShowRating = 4;
let ratingDetailList;
function toggleDetail(id) {
    const targetCard = document.getElementById(id);
    if (targetCard.classList.contains('rating-cake-card--detail'))
    {
        const targetDetail = targetCard.querySelector(".cake-card-detail-container");
        const targetDetailButton = targetCard.querySelector(".cake-card-more-detail-btn-container");
        const targetUpIcon = targetCard.querySelector(".up-icon");
        targetDetail.style.display = "none";
        targetDetailButton.style.display = "none";
        targetUpIcon.classList.remove('up-icon', 'bi-chevron-double-up');
        targetUpIcon.classList.add('down-icon', 'bi-chevron-double-down');
        targetCard.classList.remove('rating-cake-card--detail');
        targetCard.classList.add('rating-cake-card--short');
    } else {
        const targetDetail = targetCard.querySelector(".cake-card-detail-container");
        const targetDetailButton = targetCard.querySelector(".cake-card-more-detail-btn-container");
        const targetUpIcon = targetCard.querySelector(".down-icon");
        targetDetail.style.display = "block";
        targetDetailButton.style.display = "block";
        targetUpIcon.classList.remove('down-icon', 'bi-chevron-double-down');
        targetUpIcon.classList.add('up-icon', 'bi-chevron-double-up');
        targetCard.classList.add('rating-cake-card--detail');
        targetCard.classList.remove('rating-cake-card--short');
    }
}
function ShowRatingDetail(id) {
    numberShowRating=4;
   let ratingCurrentID = ratingDetailId.substring('rating-detail-'.length);
    const asideRightContent = document.getElementById('aside-right');
    const previousRatingDetail = document.getElementById(ratingDetailId);
    ratingDetailList = [];
    if (previousRatingDetail) {
        previousRatingDetail.remove();
    }
    ratingDetailId = 'rating-detail-' + id;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "rating-management", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            const newRatingDetail = document.createElement('div');
            newRatingDetail.id = 'rating-detail-' + id;
            const newRatingComment = document.createElement('div');
            let numberRating = 0;
            newRatingComment.classList.add('row');
            newRatingComment.id='rating-detail-container';
            let cakeName = '';
            for (let i = 0; i < data.length; i++) {
                if (data[i].cake.cakeID === id) {
                    ratingDetailList.push(data[i]);
                    numberRating += 1;
                    cakeName = data[i].cake.cakeName;
                    if (numberRating <= numberShowRating) {
                        newRatingComment.innerHTML += `
                            <div class="rating-comment-container col-sm-6 row">
                                <div class="col-sm-3">
                                    <img class="rating-comment-image" src="../../${data[i].customer.userAvatar}" alt="${data[i].customer.fullname}">
                                </div>                               
                                <div class="rating-comment-side-right col-sm-9">
                                    <div class="rating-comment-title">
                                        <div class="rating-comment-name">${data[i].customer.fullname}</div>
                                        <div class="rating-comment-date">${data[i].ratingDate}</div>
                                    </div>
                                    <div class="cake-card-stars-icon rating-comment-value">
                                                ${generateStars(data[i].ratingValue)}
                                    </div>
                                    <div class="rating-comment">
                                        ${data[i].comment}
                                    </div>
                                </div>
                            </div>
                        `;
                    }
                }

            }
            newRatingDetail.innerHTML += `<h3 id="rating-title">Rating of ` + cakeName + `</h3>`;
            newRatingDetail.appendChild(newRatingComment);
            if (numberShowRating < numberRating) {
                newRatingDetail.innerHTML += ` <div class="show-more-button" onclick="ShowMoreRating()">
                                <div>Show more</div>
                                <i class="bi bi-chevron-double-down down-icon"></i>
                            </div>`;
            }
            asideRightContent.appendChild(newRatingDetail);
            let moreDetailPreButton=document.getElementById(ratingCurrentID).querySelector('#moreDetailButton-'+ratingCurrentID);
            moreDetailPreButton.classList.remove("disable-icon");
           moreDetailPreButton.disable=false;
            let moreDetailButton=document.getElementById(id).querySelector('#moreDetailButton-'+id);        
           moreDetailButton.classList.add("disable-icon");
           moreDetailButton.disable=true;
        }
    };
    xhr.send();
}
function generateStars(ratingValue) {
    let starsHTML = '';
    for (let i = 1; i <= 5; i++) {
        starsHTML += `<i class = "bi bi-star${i <= ratingValue ? '-fill' : ''}"> </i>`;
    }
    return starsHTML;
}
function ShowMoreRating() {
    const ratingDetailContainer = document.getElementById('rating-detail-container');
    console.log(ratingDetailContainer);
    for (let i = numberShowRating; i < Math.min(numberShowRating + 4, ratingDetailList.length); i++) {
        let ratingDetailNew = document.createElement('div');
        ratingDetailNew.classList.add('rating-comment-container', 'col-sm-6', 'row');
        ratingDetailNew.innerHTML = `
            <div class="col-sm-3">
                <img class="rating-comment-image" src="../../${ratingDetailList[i].customer.userAvatar}" alt="${ratingDetailList[i].customer.fullname}">
            </div>                               
            <div class="rating-comment-side-right col-sm-9">
                <div class="rating-comment-title">
                    <div class="rating-comment-name">${ratingDetailList[i].customer.fullname}</div>
                    <div class="rating-comment-date">${ratingDetailList[i].ratingDate}</div>
                </div>
                <div class="cake-card-stars-icon rating-comment-value">
                    ${generateStars(ratingDetailList[i].ratingValue)}
                </div>
                <div class="rating-comment">
                    ${ratingDetailList[i].comment}
                </div>
            </div>
        `;
        ratingDetailContainer.appendChild(ratingDetailNew);
    }
    numberShowRating += 4;
    if(numberShowRating >=ratingDetailList.length){
        document.getElementsByClassName('show-more-button')[0].remove();
    }
}




