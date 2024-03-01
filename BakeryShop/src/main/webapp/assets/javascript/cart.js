let popup = document.querySelector(".popup");
let openCartClasses = document.querySelectorAll(".openCart");
let cartPopup = popup.querySelector(".cart-popup");

const openCart = () => {
    popup.classList.add("open");
};

const closeCart = () => {
    popup.classList.remove("open");
};

openCartClasses.forEach((openCartClass) => {
    openCartClass.addEventListener("click", (e) => {
        e.preventDefault();
        openCart();
    });
});

popup.addEventListener("click", closeCart);
cartPopup.addEventListener("click", (e) => e.stopPropagation());

