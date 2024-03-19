let textAreaElement = document.querySelector(".checkout-content .input #address");
let textAreaValue = textAreaElement.getAttribute("value");
textAreaElement.value = textAreaValue;

let btn = document.querySelector(".checkout-part.purchase-btn.button button");
let form = document.querySelector("#make-purchase-form");
let fullNameInput = document.querySelector("#fullname");
let emailInput = document.querySelector("#email");
let phoneInput = document.querySelector("#phone-number");
let addressInput = document.querySelector("#address");
let desInput = document.querySelector("#description");

btn.addEventListener("click", (e) => {
    if (!validate("email", emailInput) || !validate("phone", phoneInput)
            || !validate("address", addressInput) || !validate("description", desInput) || !validate("full name", fullNameInput)) {
        return false;
    }

    form.submit();
});

const validate = (type, input) => {
    let regex = /^.+$/;
    let alertText = `The ${type} must not be empty !`;

    switch (type) {
        case "email":
            regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            alertText = "Invalid email ! Please try again";
            break;
        case "phone":
            regex = /^0\d{9}$/;
            alertText = "Invalid phone number. Please enter a 10-digit valid phone number.";
            break;
        case "description":
            regex = /^/;
            break;
        default:
            regex = /^.+$/;
            alertText = `The ${type} must not be empty !`;
    }


    if (!input.value.match(regex)) {
        alert(alertText);

        input.focus();

        return false;
    }

    return true;

};
