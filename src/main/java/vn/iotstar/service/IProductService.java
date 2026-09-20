package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import vn.iotstar.dto.ProductInput;
import vn.iotstar.dto.ProductPage;
import vn.iotstar.entity.Product;

public interface IProductService {

    // 1. Hiển thị tất cả product có price từ thấp đến cao hiển thị trong trang home
    List<Product> getProductsSortedByPriceAsc();

    // 2. Lấy tất cả product của 01 category hiển thị trong trang home
    List<Product> getProductsByCategory(Long categoryId);

    // 3. Phân trang, tìm kiếm trên bảng product
    ProductPage getProductsWithPaging(Integer page, Integer size, String search, Long categoryId);

    Optional<Product> findById(Long id);

    Product createProduct(ProductInput input);

    Product updateProduct(Long id, ProductInput input);

    Boolean deleteProduct(Long id);
}
