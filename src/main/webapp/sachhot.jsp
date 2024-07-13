<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="model.KhachHang" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
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


        /*.cart-icon {*/

        /*    text-decoration: none;*/
        /*}*/

        /*.cart-text {*/
        /*    position: fixed;*/
        /*    top: 50px;*/
        /*    display: block; !* Hiển thị chữ "Giỏ hàng của bạn" dưới icon *!*/
        /*    text-align: center; !* Căn giữa văn bản *!*/
        /*    font-size: 1.1rem; !* Kích thước chữ *!*/
        /*    margin-top: 5px; !* Khoảng cách với icon *!*/
        /*    right: 20px;*/
        /*    color: black;*/
        /*}*/

        /*.fa-shopping-cart {*/
        /*    position: fixed;*/
        /*    top: 15px;*/
        /*    right: 60px;*/
        /*    cursor: pointer;*/
        /*    font-size: 2.5rem; !* Điều chỉnh kích thước theo ý muốn *!*/
        /*    color: black;*/
        /*    display: block; !* Hiển thị icon dưới dạng khối để căn giữa *!*/
        /*    margin: 0 auto;*/
        /*}*/
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
    <!-- Cart Icon -->
<%--    <a href="cart.jsp" class="cart-icon">--%>

<%--        <i class="fas fa-shopping-cart"></i>--%>
<%--        <span class="cart-text">Giỏ hàng của bạn</span>--%>
<%--    </a>--%>

    <div class="row">
        <div class="col-lg-12">
            <h3 class="text-center display-4" style="margin-bottom: 30px"> <b> Sách hot </b></h3>
            <!-- Product Listings -->
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <!-- Sản phẩm 1 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR1.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và hòn đá phù thủy</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP001" class="form-control" name="quantity" value="1"
                                       min="1">
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
                    <div class="card product-card">
                        <img src="./img/HR2.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và phòng chứa bí mật</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP002" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP002')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 3 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR3.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và tên tù ngục xứ Azkaban</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP003" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP003')">Thêm vào giỏ
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
                        <img src="./img/HR4.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Chiếc cốc lửa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP004" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP004')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 5 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR5.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hoàng Tử Lai</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP005" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP005')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 6 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR6.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Hội Phượng Hoàng</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP006" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP006')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 7 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR7.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và Bảo bối tử thần</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP007" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP007')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sản phẩm 8 -->
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR8.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Harry Potter và đứa trẻ bị nguyền rủa</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <div class="input-group">
                                <input type="number" id="quantity-SP008" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP008')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <%--                Sản phẩm 9--%>
                <div class="col">
                    <div class="card product-card">
                        <img src="./img/HR9.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Sinh Vật Huyền Bí và nơi tìm ra chúng</h5>
                            <p class="card-text">Nhà cung cấp: NXB Trẻ<br>
                                Tác giả: J.K.Rowling<br>
                                Nhà xuất bản: Trẻ</p>
                            <%--                            <form method="post" action="ThemVaoGioHang">--%>
                            <%--                                <input type="hidden" name="maSanPham" value="SP009">--%>
                            <%--                                <div class="input-group">--%>
                            <%--                                    <input type="number" class="form-control" name="quantity" value="1" min="1">--%>
                            <%--                                    <button type="submit" class="btn btn-primary">Thêm vào giỏ hàng</button>--%>
                            <%--                                </div>--%>
                            <%--                            </form>--%>
                            <div class="input-group">
                                <input type="number" id="quantity-SP009" class="form-control" name="quantity" value="1"
                                       min="1">
                                <button type="button" class="btn btn-primary" onclick="addToCart('SP001')">Thêm vào giỏ
                                    hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Các sản phẩm khác được lặp lại tương tự -->
            </div>
        </div>
    </div>
</div>
</body>
</html>


