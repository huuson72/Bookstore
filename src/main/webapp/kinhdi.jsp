<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="model.KhachHang" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sách kinh dị</title>
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
    <style>
        .card {
            width: 18rem; /* Giảm chiều rộng của thẻ sản phẩm */
            margin-bottom: 20px; /* Khoảng cách dưới của mỗi card */
        }

        .card img {
            height: 400px; /* Giảm chiều cao của hình ảnh sản phẩm */
            object-fit: cover;
        }

        .card-title {
            font-size: 1rem; /* Giảm kích cỡ chữ tiêu đề */
        }

        .card-text {
            font-size: 0.9rem; /* Kích cỡ chữ thông tin */
        }

        .add-to-cart {
            font-size: 0.9rem; /* Giảm kích cỡ chữ nút Thêm vào giỏ hàng */
        }

        .product-card {
            margin-bottom: 10px; /* Tăng khoảng cách giữa các thẻ sản phẩm */
        }
        body {
            padding-top: 70px; /* Adjust this value based on the height of your navbar */
        }
    </style>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">HStore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Trang chủ</a>
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
                <form class="d-flex align-items-center justify-content-between w-100" role="search" action="TimKiemSanPham" method="GET">

                    <div class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Nội dung tìm kiếm" aria-label="Search" name="query">
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
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Tài khoản
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="cart.jsp">Đơn hàng của tôi</a></li>
                            <li><a class="dropdown-item" href="thongtin.jsp">Thông tin</a></li>
                            <li><a class="dropdown-item" href="thaydoithongtin.jsp">Thay đổi thông tin</a></li>
                            <li><a class="dropdown-item" href="doimatkhau.jsp">Đổi mật khẩu</a></li>
                            <li><hr class="dropdown-divider"></li>
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
<div class="container mt-4">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="text-center display-4" style="margin-bottom: 30px"> <b> Kinh Dị </b></h3>
            <!-- Product Listings -->
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <!-- Sản phẩm 1 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/KD1_IT.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">It</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả:Stephen King<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP015" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP015')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 2 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/KD2_Cthulhu.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">The Call of Cthulhu</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: H.P. Lovecraft<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP016" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP016')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 3 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/KD3_madness.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">At the Mountains of Madness</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: H.P. Lovecraft<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP017" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP017')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Lặp lại cho các sản phẩm khác -->
                <!-- Sản phẩm 4 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/TL04_None.jpg" class="card-img-top" alt="...">
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
                <!-- Sản phẩm 5 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/TL04_AnMang.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Án mạng trên chuyến tàu tốc hành</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: Agatha Christie<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP020" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP020')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 6 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/TL04_Shining.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">The Shining</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: Stephen King<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP014" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP014')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
</body>
</html>
