document.addEventListener('DOMContentLoaded', function () {
    const dropdownItems = document.getElementById('dropdownItems');
    const cakes = document.getElementById('cakeList').getElementsByClassName('cake-in-order-cover');

    function selectItem(item) {
        document.getElementById('dropdownMenuButton').textContent = item;
        filterByType(item);
    }

    function filterByType(type) {
        let hiddenCount = 0;
        
        hideNotice();

        for (const cake of cakes) {
            const cakeType = cake.getAttribute('data-type');

            if (type === 'All' || type === cakeType) {
                cake.style.display = 'block';
            } else {
                cake.style.display = 'none';
                hiddenCount++;
            }
        }

        if (hiddenCount === cakes.length) {
            showNotice();
        } else {
            hideNotice();
        }
    }

    function showNotice() {
        console.log('All cakes are hidden!');
        document.getElementById('empty-notice-txt').style.display = 'block';
    }

    function hideNotice() {
        console.log('At least one cake is visible!');
        document.getElementById('empty-notice-txt').style.display = 'none';
    }

    const dropdownItemsDOM = document.querySelectorAll('.dropdown-item');
    dropdownItemsDOM.forEach(item => {
        item.addEventListener('click', () => {
            const selectedItem = item.textContent;
            selectItem(selectedItem);
        });
    });
});
