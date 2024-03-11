    function redirectToToppingDetailPage(toppingID) {
        console.log("hello");
    var encodedToppingID = encodeURIComponent(toppingID);
    var url = 'ToppingDetailManagement?toppingID=' + encodedToppingID;
    console.log("Redirecting to URL:", url);
    window.location.href = url;
    }
    
    function redirectToAddToppingPage() {
    var url = 'AddTopping';
    console.log("Redirecting to URL:", url);
    window.location.href = url;
    }