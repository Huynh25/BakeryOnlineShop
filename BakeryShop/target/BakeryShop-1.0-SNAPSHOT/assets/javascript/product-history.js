let activeNavFilter="all";
let currentPage=1;
function performFilter(filter) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "product-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
            if (filter !== "search" && filter!=="date" && filter !=="back-page" && filter!== "next-page") {
                let preActiveFilterButton = document.getElementsByClassName("nav-bar--active")[0];
                let nextActiveFilterButton = document.getElementById(filter + "-filter");
                preActiveFilterButton.querySelector(".nav-bar-items").disabled = false;
                nextActiveFilterButton.querySelector(".nav-bar-items").disabled = true;
                preActiveFilterButton.classList.remove('nav-bar--active');
                nextActiveFilterButton.classList.add('nav-bar--active');
            }
            document.getElementById('numberProduct').innerHTML = `${data.numberAllPage} Products`;
            document.getElementById('showCurrentPage').innerHTML = `Page ${currentPage} of ${data.allPage===0?"1":data.allPage}`;
            document.getElementById('next-page-btn').remove();
            document.getElementById('back-page-btn').remove();
            if(currentPage ===1){
                document.getElementById('pagination').innerHTML += ` <i id="back-page-btn" class="bi bi-chevron-left disable-icon" ></i>`;
            }else{
                document.getElementById('pagination').innerHTML += ` <i id="back-page-btn" class="bi bi-chevron-left" onclick="performFilter('back-page')"></i>`;
            }
            if (data.allPage > currentPage) {
                document.getElementById('pagination').innerHTML += `<i id="next-page-btn" onclick="performFilter('next-page')" class="bi bi-chevron-right"></i>`;
            } else {
                document.getElementById('pagination').innerHTML += `<i id="next-page-btn" class="bi bi-chevron-right disable-icon"></i>`;
            }
          
            document.getElementById('product-tbody').remove();
            const table = document.getElementById('table');
            const productTbody = document.createElement('tbody');
            productTbody.id = 'product-tbody';
            const phList = data.phList;
            const phListLength = phList.length;
            if(phList.length===0){
                table.querySelector("thead").style="display:none";
                productTbody.innerHTML+=`<h2>There aren't any Product History</h2>`;
            }
            for (let i = 0; i < phListLength; i++) {
                const isCake = phList[i].cake !== undefined ? true : false;
                console.log(isCake + " " + i);
                productTbody.innerHTML += `<tr>
                                        <td>
                                            <div class="product-content">                                    
                                                <div class="product-info">
                                                    <div class="product-name-id">
                                                        <div>${isCake ? "Cake" : "Topping"}</div>
                                                    <div>#${isCake ? phList[i].cake.cakeID : phList[i].topping.toppingID}</div>
                                                </div>
                                                <div>${isCake ? phList[i].cake.cakeName : phList[i].topping.toppingName}</div>
                                            </div>
                                            <div><img class="product-image" src="../../${isCake ? phList[i].cake.cakeImg : phList[i].topping.toppingImg}" alt="${isCake ? phList[i].cake.cakeName : phList[i].topping.toppingName}"/></div>
                                        </div>
                                    </td>
                                    <td >
                                        <div class="staff-content">                                   
                                            <div class="staff-info">
                                                <div>#${phList[i].updateBy !== undefined ? phList[i].updateBy.staffID : phList[i].createBy.staffID}</div>
                                                <div>${phList[i].updateBy !== undefined ? phList[i].updateBy.fullname : phList[i].createBy.fullname}</div>
                                            </div>
                                            <div><img class="staff-image" src="../../${phList[i].updateBy !== undefined ? phList[i].updateBy.staffAvatar : phList[i].createBy.staffAvatar}" alt="alt"/></div>
                                        </div>
                                    </td>
                                    <td class="product-update-date">
                                        ${convertDateFormat(phList[i].updatedDate !== undefined ? phList[i].updatedDate : phList[i].createDate)}
                                    </td>
                                    <td class="product-update-quantity">
                                        ${phList[i].phQuantity}
                                    </td>
                                    <td  class="product-update-status">
                                        ${phList[i].updatedDate !== undefined ? "Update" : "Create"}
                                    </td>
                                </tr>`;
            }
            table.appendChild(productTbody);
        }
    };
    if(filter!=="search" && filter!=="date"){
         if (filter === "next-page") {
            currentPage += 1;
        } else if (filter === "back-page") {
            currentPage -= 1;
        } else {
            activeNavFilter = filter;
            currentPage = 1;
        }
    }
    const searchInputValue=document.getElementById('search-product-input').value;
    const dateValue=document.getElementById('date-filter-value').value;
    xhr.send("filter=" + activeNavFilter+"&search="+searchInputValue+"&date="+dateValue+"&currentPage="+currentPage);
}

function convertDateFormat(dateString) {
    if (!dateString)
        return "Undefined";
    var date = new Date(dateString);
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);
    var formattedDate = year + "-" + month + "-" + day;

    return formattedDate;
}