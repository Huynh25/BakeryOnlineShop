
function increaseQuantity() {
    var quantityInput = document.getElementById('cake-quantity');
    var currentQuantity = parseInt(quantityInput.value);
    if (!isNaN(currentQuantity)) {
        quantityInput.value = currentQuantity + 1;
    }
}
function increasePrice() {
    var quantityInput = document.getElementById('cake-price');
    var currentQuantity = parseInt(quantityInput.value);
    if (!isNaN(currentQuantity)) {
        quantityInput.value = currentQuantity + 1000;
    }
}
function addTopping() {
    var selectedTopping = document.getElementById("add-topping-select").value;

    if (selectedTopping && thisToppingList.indexOf(selectedTopping) === -1) {
        thisToppingList.push(selectedTopping);

        // Remove the topping from the dropdown
        var dropdown = document.getElementById("add-topping-select");
        for (var i = 0; i < dropdown.options.length; i++) {
            if (dropdown.options[i].value === selectedTopping) {
                dropdown.remove(i);
                break;
            }
        }

        var toppingContainer = document.getElementById("topping-container");
        var newToppingDiv = document.createElement("div");
        newToppingDiv.className = "topping-container col-sm-4";
        newToppingDiv.innerHTML = `
            <p class="topping-in-go-with-txt">${selectedTopping}</p>
            <button type="button" class="cancel-btn close" aria-label="Close" onclick="removeTopping('${selectedTopping}')">
                <span aria-hidden="true">&times;</span>
            </button>
        `;

        // Append the new topping to the container
        toppingContainer.appendChild(newToppingDiv);
    }
}
document.getElementById("this-topping-list").value = JSON.stringify(thisToppingList);

// Function to remove a topping from the list
function removeTopping(toppingToRemove) {

    // Remove the topping from the list
    thisToppingList = thisToppingList.filter(topping => topping !== toppingToRemove);

    // Add the topping back to the dropdown options if it doesn't exist
    var dropdown = document.getElementById("add-topping-select");

    // Check if the topping already exists in the dropdown
    var optionExists = Array.from(dropdown.options).some(option => option.value === toppingToRemove);

    if (!optionExists) {
        var newOption = document.createElement("option");
        newOption.value = toppingToRemove;
        newOption.text = toppingToRemove;
        dropdown.add(newOption);
    }

    // Remove the corresponding HTML element
    var toppingContainer = document.getElementById("topping-container");
    var toppings = toppingContainer.getElementsByClassName("topping-container");

    for (var i = 0; i < toppings.length; i++) {
        var toppingDiv = toppings[i];
        var toppingText = toppingDiv.querySelector(".topping-in-go-with-txt").innerText;

        if (toppingText === toppingToRemove) {
            toppingContainer.removeChild(toppingDiv);
            break;
        }
    }
}

function sendDataToServlet() {

    document.getElementById("cake-name-title").innerHTML = document.getElementById('cake-name').value;
    var url = 'CakeDetailManagement';  // Thay thế bằng URL thực tế của servlet

    // Lấy đường dẫn ảnh gốc và hiện tại
    var originalImageUrl = removeLocalhost(document.getElementById('cake-image').getAttribute('data-original-image'));
    var currentImageUrl = removeLocalhost(document.getElementById('cake-image').src);

    // Kiểm tra xem ảnh đã thay đổi hay chưa
    var imageChanged = (currentImageUrl !== originalImageUrl);

    // Lấy giá trị từ các trường input
    var formData = {
        'cake-id': document.getElementById('cake-id').value,
        'cake-name': document.getElementById('cake-name').value,
        'cake-type': document.getElementById('cake-type').value,
        'cake-quantity': document.getElementById('cake-quantity').value,
        'cake-price': document.getElementById('cake-price').value,
        'cake-description': document.getElementById('cake-description').value,
        'thisToppingList': thisToppingList,
        'cake-image-changed': imageChanged,
        'cake-image-url': currentImageUrl
    };

    // Chuyển đổi formData thành JSON
    var jsonData = JSON.stringify(formData);

    // Tạo một đối tượng XMLHttpRequest mới
    var xhr = new XMLHttpRequest();

    // Cài đặt yêu cầu
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    // Cài đặt xử lý sự kiện cho khi yêu cầu hoàn tất
    xhr.onload = function () {
        if (xhr.status === 200) {
            // Yêu cầu thành công
            console.log('Data sent successfully');
            showContactPopup();
        } else {
            // Yêu cầu thất bại
            console.error('Failed to send data to the servlet');
        }
    };

    // Gửi yêu cầu với dữ liệu JSON
    xhr.send(jsonData);
    closePopup();

}

document.getElementById("submitBtn").addEventListener("click", sendDataToServlet);

function openImageUploader() {
    document.getElementById('cake-image-upload').click();
}

function handleImageUpload(event) {
    const selectedFile = event.target.files[0];

    // Kiểm tra xem có tệp tin nào đã chọn hay không
    if (!selectedFile) {
        console.error('No file selected.');
        return;
    }

    // Tạo đường dẫn của ảnh dựa trên tên tệp tin và lưu vào biến
    var imageUrl = 'Image/Cake/' + selectedFile.name;

    // Hiển thị ảnh mới
    document.getElementById('cake-image').src = imageUrl;

    // Gửi dữ liệu đến servlet hoặc lưu vào DB theo yêu cầu của bạn
//    sendDataToServlet(imageUrl);
}
function removeLocalhost(url) {
    return url.replace('http://localhost:8080/', '');
}

function isValidForm() {
    if (!document.getElementById('cake-name').value) {
        document.getElementById("cake-name-error").innerHTML = "Please fill in Cake Name";
    } else if (!document.getElementById('cake-type').value) {
        document.getElementById("cake-type-error").innerHTML = "Please fill in Cake Type";
    } else if (document.getElementById('cake-quantity').value < 1) {
        document.getElementById("cake-quantity-error").innerHTML = "greater than 0";
    } else if (document.getElementById('cake-price').value < 1) {
        document.getElementById("cake-price-error").innerHTML = "greater than 0";
    } else if (!document.getElementById('cake-description').value) {
        document.getElementById("cake-description-error").innerHTML = "Please fill in Cake Description";
        return false;
    } else
        return true;

}

function resetError(errorElement) {
    document.getElementById(errorElement).innerHTML = "";
}

function showPopup() {
    if (isValidForm())
    {
        document.getElementById("acceptOrderPopup").style.display = "block";
    }
}

function closePopup() {
    document.getElementById("acceptOrderPopup").style.display = "none";
}


function showContactPopup() {
    document.getElementById("contactPopup").style.display = "block";
}

function closeContactPopup() {
    document.getElementById("contactPopup").style.display = "none";
}


function sendDataToAddCakeServlet() {

    document.getElementById("cake-name-title").innerHTML = document.getElementById('cake-name').value;
    var url = 'AddCake';  // Thay thế bằng URL thực tế của servlet

    // Lấy đường dẫn ảnh gốc và hiện tại
    var originalImageUrl = removeLocalhost(document.getElementById('cake-image').getAttribute('data-original-image'));
    var currentImageUrl = removeLocalhost(document.getElementById('cake-image').src);

    // Kiểm tra xem ảnh đã thay đổi hay chưa
    var imageChanged = (currentImageUrl !== originalImageUrl);

    // Lấy giá trị từ các trường input
    var formData = {
        'cake-id': document.getElementById('cake-id').value,
        'cake-name': document.getElementById('cake-name').value,
        'cake-type': document.getElementById('cake-type').value,
        'cake-quantity': document.getElementById('cake-quantity').value,
        'cake-price': document.getElementById('cake-price').value,
        'cake-description': document.getElementById('cake-description').value,
        'thisToppingList': thisToppingList,
        'cake-image-changed': imageChanged,
        'cake-image-url': currentImageUrl
    };

    // Chuyển đổi formData thành JSON
    var jsonData = JSON.stringify(formData);

    // Tạo một đối tượng XMLHttpRequest mới
    var xhr = new XMLHttpRequest();

    // Cài đặt yêu cầu
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    // Cài đặt xử lý sự kiện cho khi yêu cầu hoàn tất
    xhr.onload = function () {
        if (xhr.status === 200) {
            // Yêu cầu thành công
            console.log('Data sent successfully');
            showContactPopup();
        } else {
            // Yêu cầu thất bại
            console.error('Failed to send data to the servlet');
        }
    };

    // Gửi yêu cầu với dữ liệu JSON
    xhr.send(jsonData);
    closePopup();

}