package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Hiển thị tất cả product có price từ thấp đến cao
    List<Product> findAllByOrderByUnitPriceAsc();

    // 2. Lấy tất cả product của 01 category
    List<Product> findByCategory_CategoryId(Long categoryId);

    // Lấy tất cả product của 01 category sắp xếp theo giá tăng dần
    List<Product> findByCategory_CategoryIdOrderByUnitPriceAsc(Long categoryId);

    // 3. Tìm kiếm theo tên sản phẩm có phân trang
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    // Tìm kiếm theo tên sản phẩm kết hợp danh mục có phân trang
    Page<Product> findByCategory_CategoryIdAndProductNameContaining(Long categoryId, String productName, Pageable pageable);

    // Lọc theo danh mục có phân trang
    Page<Product> findByCategory_CategoryId(Long categoryId, Pageable pageable);
}
