package vn.iotstar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.iotstar.dto.CategoryInput;
import vn.iotstar.dto.CategoryPage;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.ICategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryId"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryPage getCategoriesWithPaging(Integer page, Integer size, String search) {
        int pageIndex = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : 5;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "categoryId"));

        Page<Category> categoryPage;
        if (search != null && !search.trim().isEmpty()) {
            categoryPage = categoryRepository.findByCategoryNameContaining(search.trim(), pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }

        return CategoryPage.builder()
                .content(categoryPage.getContent())
                .totalPages(categoryPage.getTotalPages())
                .totalElements((int) categoryPage.getTotalElements())
                .currentPage(categoryPage.getNumber())
                .pageSize(categoryPage.getSize())
                .build();
    }

    @Override
    public Category createCategory(CategoryInput input) {
        Category category = new Category();
        category.setCategoryName(input.getCategoryName());
        category.setIcon(input.getIcon() != null ? input.getIcon() : "default-icon.png");
        category.setStatus(input.getStatus() != null ? input.getStatus() : (short) 1);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryInput input) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            Category category = optCategory.get();
            if (input.getCategoryName() != null && !input.getCategoryName().trim().isEmpty()) {
                category.setCategoryName(input.getCategoryName());
            }
            if (input.getIcon() != null && !input.getIcon().trim().isEmpty()) {
                category.setIcon(input.getIcon());
            }
            if (input.getStatus() != null) {
                category.setStatus(input.getStatus());
            }
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
