/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

  // Mảng chứa các item của dropdown
  const dropdownItems = ['All','Item 1', 'Item 2', 'Item 3', 'Item 4'];

  // Lặp qua mảng và thêm các item vào dropdown
  dropdownItems.forEach(item => {
    const dropdownItem = document.createElement('a');
    dropdownItem.classList.add('dropdown-item');
    dropdownItem.href = '#';
    dropdownItem.textContent = item;
    dropdownItem.addEventListener('click', () => selectItem(item));
    document.getElementById('dropdownItems').appendChild(dropdownItem);
  });

  // Hàm xử lý khi một item được chọn
  function selectItem(item) {
    document.getElementById('dropdownMenuButton').textContent = item;
  }

