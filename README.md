# Spring Boot GraphQL & Thymeleaf Product Manager

Dự án phát triển ứng dụng Web quản lý Danh mục (Category) và Sản phẩm (Product) sử dụng **Spring Boot**, **Spring GraphQL**, **Thymeleaf**, **AJAX (jQuery)** và cơ sở dữ liệu **SQL Server**.

## Tính năng chính

1. **Trang chủ (Home Page):**
   - **Hiển thị sản phẩm sắp xếp theo giá từ thấp đến cao (Sort Price ASC)**: Được kích hoạt linh hoạt qua GraphQL Query và AJAX.
   - **Lọc sản phẩm theo danh mục (Filter by Category)**: Chọn bất kỳ danh mục nào để tải sản phẩm tương ứng mà không cần tải lại trang.
   - Hiển thị danh sách sản phẩm dưới dạng Card UI trực quan, responsive với Bootstrap 5.

2. **Quản lý Sản phẩm (Product Management):**
   - **CRUD**: Thêm mới, cập nhật thông tin sản phẩm và xóa sản phẩm qua GraphQL Mutation & AJAX Modal.
   - **Tìm kiếm**: Tìm kiếm theo tên sản phẩm (`productName`) theo thời gian thực.
   - **Phân trang (Pagination)**: Phân trang linh hoạt với kích thước trang tùy chỉnh.

3. **Quản lý Danh mục (Category Management):**
   - **CRUD**: Thêm mới, cập nhật và xóa danh mục qua GraphQL Mutation & AJAX.
   - **Tìm kiếm & Phân trang**: Hỗ trợ tìm kiếm theo tên danh mục và phân trang dữ liệu.

4. **GraphQL & GraphiQL:**
   - Cung cấp API GraphQL với đầy đủ Schema types, inputs, queries và mutations.
   - Hỗ trợ giao diện GraphiQL tương tác trực tiếp tại `/graphiql`.

---

## Công nghệ sử dụng

- **Backend Framework**: Spring Boot 4.x / Spring Framework 7
- **Data Persistence**: Spring Data JPA, Hibernate ORM (`jakarta.persistence.*`)
- **API**: Spring GraphQL (`spring-boot-starter-graphql`), GraphQL Java
- **Database**: Microsoft SQL Server (sử dụng database `springboot1_7_db`)
- **Frontend Template**: Thymeleaf 3
- **Client Scripting**: JavaScript, jQuery AJAX
- **CSS Framework**: Bootstrap 5, FontAwesome 6

---

## Cấu trúc dự án

```
springboot1-9/
├── database/
│   └── springboot1_7_db.sql        # Script khởi tạo cơ sở dữ liệu & dữ liệu mẫu Unicode
├── src/main/
│   ├── java/vn/iotstar/
│   │   ├── config/                 # Cấu hình hệ thống & GraphQL
│   │   ├── controller/
│   │   │   ├── graphql/            # GraphQL Controllers (@QueryMapping, @MutationMapping)
│   │   │   └── web/                # Thymeleaf Web Controllers
│   │   ├── dto/                    # Data Transfer Objects & GraphQL Inputs
│   │   ├── entity/                 # JPA Entities (Category, Product)
│   │   ├── repository/             # Spring Data JPA Repositories
│   │   └── service/                # Business Service Layer & Implementations
│   └── resources/
│       ├── application.properties  # Cấu hình kết nối SQL Server, GraphQL, Thymeleaf
│       ├── graphql/
│       │   └── schema.graphqls     # GraphQL Schema Definition
│       ├── static/                 # CSS, JS, hình ảnh
│       └── templates/              # Giao diện Thymeleaf (.html)
└── pom.xml
```

---

## Cài đặt & Chạy ứng dụng

### 1. Cơ sở dữ liệu SQL Server
- Tài khoản: `sa` / Mật khẩu: `123456`
- Chạy script trong file `database/springboot1_7_db.sql` trong SQL Server Management Studio (SSMS) hoặc qua `sqlcmd`.

### 2. Khởi chạy ứng dụng
Mở terminal tại thư mục gốc của project:
```bash
mvn clean compile
mvn spring-boot:run
```

### 3. Truy cập ứng dụng
- **Trang chủ**: [http://localhost:8080/](http://localhost:8080/) hoặc [http://localhost:8080/home](http://localhost:8080/home)
- **Quản lý Sản phẩm**: [http://localhost:8080/admin/products](http://localhost:8080/admin/products)
- **Quản lý Danh mục**: [http://localhost:8080/admin/categories](http://localhost:8080/admin/categories)
- **GraphiQL UI Playground**: [http://localhost:8080/graphiql](http://localhost:8080/graphiql)
