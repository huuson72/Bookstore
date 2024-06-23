<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="model.KhachHang" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="main.js"></script>
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<div class="img_header">
    <img src="./img/headerimg.png" alt="">
</div>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
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
                        <li><a class="dropdown-item" href="#">Trinh thám</a></li>
                        <li><a class="dropdown-item" href="#">Kinh dị</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Truyện tranh</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Thông báo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Tạm hết</a>
                </li>
            </ul>

            <ul class="navbar-nav mb-2 mb-lg-0">
                <form class="d-flex align-items-center justify-content-between w-100" role="search">
                    <div class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Nội dung tìm kiếm" aria-label="Search">
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
                    <div class="d-flex align-items-center ms-3 position-relative">
                        <b><%= khachHang.getHoVaTen() %></b>
                        <span class="mx-3">|</span>
<%--                        <a class="btn btn-link logout-link" href="#" onclick="showLogoutConfirmation()">Đăng xuất</a>--%>
<%--                        <div id="logout-confirmation" class="logout-confirmation d-none">--%>
<%--                            <p>Bạn có chắc chắn muốn đăng xuất không?</p>--%>

<%--                            <form action="dang-xuat" method="POST">--%>
<%--                                <button class="w-100 btn btn-lg btn-primary" type="submit">Đăng xuất</button>--%>
<%--                            </form>--%>

<%--                        </div>--%>

                        <div class="row"><a style="white-space: nowrap; text-decoration: none" href="dang-xuat">
                            Đăng xuất
                        </a>
                        </div>
                    </div>
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
                <a href="#" class="list-group-item list-group-item-action">Sách hot</a>
                <a href="#" class="list-group-item list-group-item-action">Lượt mua nhiều nhất</a>
                <a href="#" class="list-group-item list-group-item-action">Giá tốt nhất</a>
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
            <!-- Product -->
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HP1.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và hòn đá phù thủy</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR2.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và phòng chứa bí mật</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR3.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và tên tù ngục xứ Azkaban</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR4.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Chiếc cốc lửa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR6.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hoàng Tử Lai</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR5.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hội Phượng Hoàng</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR7.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và bảo bối tử thần</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR8.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và đứa con bị nguyền rủa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="./img/HR9.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Tuyển tập trọn bộ Harry Potter</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Product -->
        </div>
        <!-- End Slider and Product -->
    </div>
    <!-- Footer -->
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Trang chủ</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định giao hàng</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định trả hàng</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Liên hệ</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Blogs</a></li>
        </ul>
        <p class="text-center text-muted">© 2024 HSTore</p>
    </footer>
    <!-- End footer -->
</div>
</body>
</html>

<!-- End Page content -->

