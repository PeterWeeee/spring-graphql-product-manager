package vn.iotstar.controller.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.iotstar.dto.ProductInput;
import vn.iotstar.dto.ProductPage;
import vn.iotstar.entity.Product;
import vn.iotstar.service.IProductService;

@Controller
public class ProductGraphQLController {

    @Autowired
    private IProductService productService;

    // 1. Hiển thị tất cả product có price từ thấp đến cao hiển thị trong trang home
    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productService.getProductsSortedByPriceAsc();
    }

    // 2. Lấy tất cả product của 01 category hiển thị trong trang home
    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    // 3. Phân trang và tìm kiếm Product
    @QueryMapping
    public ProductPage products(@Argument Integer page, @Argument Integer size,
                                @Argument String search, @Argument Long categoryId) {
        return productService.getProductsWithPaging(page, size, search, categoryId);
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productService.findById(id).orElse(null);
    }

    // 3. CRUD Mutation
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        return productService.createProduct(input);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        return productService.updateProduct(id, input);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }
}
