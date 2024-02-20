function toggleDetail(id){
    const targetCard= document.getElementById(id);
    if(targetCard.classList.contains('rating-cake-card--detail'))
    {
    const targetDetail=targetCard.querySelector(".cake-card-detail-container");
    const targetDetailButton=targetCard.querySelector(".cake-card-more-detail-btn-container");
    const targetUpIcon=targetCard.querySelector(".up-icon");
    targetDetail.style.display="none";
    targetDetailButton.style.display="none";
    targetUpIcon.classList.remove('up-icon','bi-chevron-double-up');
    targetUpIcon.classList.add('down-icon','bi-chevron-double-down');
    targetCard.classList.remove('rating-cake-card--detail');
    targetCard.classList.add('rating-cake-card--short');
    }else{
        const targetDetail=targetCard.querySelector(".cake-card-detail-container");
        const targetDetailButton=targetCard.querySelector(".cake-card-more-detail-btn-container");
        const targetUpIcon=targetCard.querySelector(".down-icon");
        targetDetail.style.display="block";
        targetDetailButton.style.display="block";
        targetUpIcon.classList.remove('down-icon','bi-chevron-double-down');
        targetUpIcon.classList.add('up-icon','bi-chevron-double-up');
        targetCard.classList.add('rating-cake-card--detail');
        targetCard.classList.remove('rating-cake-card--short'); 
    }
}