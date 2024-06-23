function showLogoutConfirmation() {
    document.getElementById('logout-confirmation').classList.remove('d-none');
}

function hideLogoutConfirmation() {
    document.getElementById('logout-confirmation').classList.add('d-none');
}

function confirmLogout() {
    if (!confirm("Bạn có chắc chắn muốn đăng xuất?")) {
        event.preventDefault(); // Ngăn chặn chuyển hướng mặc định của thẻ <a>
    }
}


function showLogoutConfirmation() {
    var confirmation = document.getElementById('logout-confirmation');
    confirmation.classList.toggle('d-none'); // Toggle class to show/hide
}
