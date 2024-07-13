<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="model.KhachHang" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="main.js"></script>
    <link rel="stylesheet" href="./css/main.css">
    <%--<style>--%>
    <%--    .nav-item.dropdown .dropdown-toggle::after {--%>
    <%--        content: "";--%>
    <%--        display: inline-block;--%>
    <%--        margin-left: 0.255em; /* Điều chỉnh khoảng cách giữa chữ "Tài khoản" và mũi tên */--%>
    <%--        vertical-align: 0.255em; /* Điều chỉnh căn lề dọc của mũi tên */--%>
    <%--        border-top: 0.3em solid;--%>
    <%--        border-right: 0.3em solid transparent;--%>
    <%--        border-bottom: 0;--%>
    <%--        border-left: 0.3em solid transparent;--%>
    <%--    }--%>

    <%--</style>--%>
</head>
<body>
<div class="img_header">
    <img src="./img/headerimg.png" alt="Header Image">
</div>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary ">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">HStore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Thể loại
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="trinhtham.jsp">Trinh thám</a></li>
                        <li><a class="dropdown-item" href="kinhdi.jsp">Kinh dị</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="truyentranh.jsp">Truyện tranh</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Thông báo</a>
                </li>
            </ul>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <%--                <form class="d-flex align-items-center justify-content-between w-100" role="search">--%>
                <form class="d-flex align-items-center justify-content-between w-100" role="search"
                      action="TimKiemSanPham" method="GET">

                    <div class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Nội dung tìm kiếm"
                               aria-label="Search" name="query">
                        <button class="btn btn-outline-success" type="submit">Tìm</button>
                    </div>
                    <%
                        Object obj = session.getAttribute("khachHang");
                        KhachHang khachHang = null;
                        if (obj != null)
                            khachHang = (KhachHang) obj;

                        if (khachHang == null) {
                    %>
                    <div class="d-flex">
                        <a class="btn btn-link" href="dangnhap.jsp">Đăng nhập</a>
                        <a class="btn btn-link me-2" href="dangky.jsp">Đăng ký</a>
                    </div>
                    <% } else { %>
                    <%--                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">--%>
                    <li class="nav-item dropdown dropstart">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Tài khoản
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="cart.jsp">Đơn hàng của tôi</a></li>
                            <li><a class="dropdown-item" href="thongtin.jsp">Thông tin</a></li>
                            <li><a class="dropdown-item" href="thaydoithongtin.jsp">Thay đổi thông tin</a></li>
                            <li><a class="dropdown-item" href="doimatkhau.jsp">Đổi mật khẩu</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="dang-xuat">Thoát tài khoản</a></li>
                        </ul>
                    </li>


                    <%--                    </ul>--%>
                    <% } %>
                </form>

            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->


<!-- Page content -->
<div class="container mt-4">
    <div class="row">
        <!-- Menu left -->
        <div class="col-lg-3">
            <div class="list-group">
                <a href="sachhot.jsp" class="list-group-item list-group-item-action">Sách hot</a>
                <a href="truyentranh.jsp" class="list-group-item list-group-item-action">Lượt mua nhiều nhất</a>
                <a href="trinhtham.jsp" class="list-group-item list-group-item-action">Giá tốt nhất</a>
            </div>
        </div>
        <!-- End Menu left -->

        <!-- Slider and Product-->
        <div class="col-lg-8">
            <!-- Slider -->
            <div id="carouselExampleIndicators" class="carousel slide mb-4" data-bs-ride="true">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="0" class="active" aria-current="true"
                            aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators"
                            data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="./img/Slider1.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="./img/Slider2.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="./img/Slider3.png" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button"
                        data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button"
                        data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!-- End Slider -->

        </div>
        <!-- End Slider and Product -->
    </div>
    <%--    Product--%>
    <div class="row">
        <div class="col-lg-12">
            <!-- Product Listings -->
            <div class="row row-cols-1 row-cols-md-4 g-4">
                <!-- Sản phẩm 1 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR1.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và hòn đá phù thủy</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP001" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP001')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Các sản phẩm khác được lặp lại tương tự -->
                <!-- Sản phẩm 2 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <div class="img-container">
                                <img src="./img/HR2.png" class="card-img-top" alt="...">
                            </div>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và phòng chứa bí mật</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP002" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP002')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 3 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR3.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và tên tù ngục xứ Azkaban</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP003" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP003')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 4 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR4.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Chiếc cốc lửa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP004" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP004')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 5 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR5.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hoàng Tử Lai</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP005" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP005')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 6 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR6.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hội Phượng Hoàng</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP006" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP006')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 7 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR7.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Bảo bối tử thần</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP007" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP007')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 8 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR8.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và đứa trẻ bị nguyền rủa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP008" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP008')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 9 -->
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/HR9.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Sinh Vật Huyền Bí và nơi tìm ra chúng</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP009" class="form-control quantity-input"
                                       name="quantity" value="1" min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP009')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%--                Sản phẩm 10--%>
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/TL04_Shining.jpg" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">The Shining</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: Stephen King<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP014" class="form-control quantity-input"
                                       name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP014')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                Sản phẩm 11--%>
                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/TL04_None.jpg" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">And Then There Were None</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: Agatha Christie<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP013" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP013')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card product-card h-100 d-flex flex-column">
                        <div class="img-container">
                            <img src="./img/TL04_THeHound.jpg" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">The Hound of the Baskervilles</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: Arthur Conan Doyle<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP012" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP012')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <!-- End Product -->
    <!-- Footer -->
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Trang chủ</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định giao hàng</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định trả hàng</a></li>
            <li class="nav-item"><a href="https://www.facebook.com/hson72/" class="nav-link px-2 text-muted">Liên hệ</a>
            </li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Blogs</a></li>
        </ul>
        <p class="text-center text-muted">© 2024 HSTore
            <a href="https://www.facebook.com/hson72/" class="text-muted ms-2">
                <i class="fab fa-facebook"></i>
            </a>
            <a href="https://mail.google.com/mail/u/4/#inbox" class="text-muted ms-2">
                <i class="fas fa-envelope"></i>
            </a>
        </p>


    </footer>
    <!-- End footer -->
</div>
</body>
</html>

<!-- End Page content -->

