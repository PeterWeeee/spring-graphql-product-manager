package vn.iotstar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.iotstar.dto.ProductInput;
import vn.iotstar.dto.ProductPage;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsSortedByPriceAsc() {
        return productRepository.findAllByOrderByUnitPriceAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductPage getProductsWithPaging(Integer page, Integer size, String search, Long categoryId) {
        int pageIndex = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : 6;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "productId"));

        Page<Product> productPage;
        boolean hasCategory = (categoryId != null && categoryId > 0);
        boolean hasSearch = (search != null && !search.trim().isEmpty());

        if (hasCategory && hasSearch) {
            productPage = productRepository.findByCategory_CategoryIdAndProductNameContaining(categoryId, search.trim(), pageable);
        } else if (hasCategory) {
            productPage = productRepository.findByCategory_CategoryId(categoryId, pageable);
        } else if (hasSearch) {
            productPage = productRepository.findByProductNameContaining(search.trim(), pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        return ProductPage.builder()
                .content(productPage.getContent())
                .totalPages(productPage.getTotalPages())
                .totalElements((int) productPage.getTotalElements())
                .currentPage(productPage.getNumber())
                .pageSize(productPage.getSize())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(ProductInput input) {
        Product product = new Product();
        product.setProductName(input.getProductName());
        product.setQuantity(input.getQuantity() != null ? input.getQuantity() : 0);
        product.setUnitPrice(input.getUnitPrice() != null ? input.getUnitPrice() : 0.0);
        product.setImages(input.getImages() != null ? input.getImages() : "default-product.png");
        product.setDescription(input.getDescription() != null ? input.getDescription() : "");
        product.setDiscount(input.getDiscount() != null ? input.getDiscount() : 0.0);
        product.setCreateDate(new Date());
        product.setStatus(input.getStatus() != null ? input.getStatus() : (short) 1);

        if (input.getCategoryId() != null) {
            categoryRepository.findById(input.getCategoryId()).ifPresent(product::setCategory);
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductInput input) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            if (input.getProductName() != null && !input.getProductName().trim().isEmpty()) {
                product.setProductName(input.getProductName());
            }
            if (input.getQuantity() != null) {
                product.setQuantity(input.getQuantity());
            }
            if (input.getUnitPrice() != null) {
                product.setUnitPrice(input.getUnitPrice());
            }
            if (input.getImages() != null && !input.getImages().trim().isEmpty()) {
                product.setImages(input.getImages());
            }
            if (input.getDescription() != null) {
                product.setDescription(input.getDescription());
            }
            if (input.getDiscount() != null) {
                product.setDiscount(input.getDiscount());
            }
            if (input.getStatus() != null) {
                product.setStatus(input.getStatus());
            }
            if (input.getCategoryId() != null) {
                categoryRepository.findById(input.getCategoryId()).ifPresent(product::setCategory);
            }
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
