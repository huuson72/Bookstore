// function showLogoutConfirmation() {
//     document.getElementById('logout-confirmation').classList.remove('d-none');
// }
//
// function hideLogoutConfirmation() {
//     document.getElementById('logout-confirmation').classList.add('d-none');
// }
//
// function confirmLogout() {
//     if (!confirm("Bạn có chắc chắn muốn đăng xuất?")) {
//         event.preventDefault(); // Ngăn chặn chuyển hướng mặc định của thẻ <a>
//     }
// }
//
//
// function showLogoutConfirmation() {
//     var confirmation = document.getElementById('logout-confirmation');
//     confirmation.classList.toggle('d-none'); // Toggle class to show/hide
// }
// function refreshPage() {
//     // Xóa cache và lịch sử trình duyệt
//     window.history.replaceState({}, document.title, "/");
//     // Làm mới trang để ngăn cache
//     location.reload(true);
// }
function addToCart(maSanPham) {
    var quantity = document.getElementById("quantity-" + maSanPham).value;
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "ThemVaoGioHang", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Thêm vào giỏ hàng thành công");
        }
    };

    xhr.send("maSanPham=" + maSanPham + "&quantity=" + quantity);
}

