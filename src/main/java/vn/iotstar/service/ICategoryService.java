package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import vn.iotstar.dto.CategoryInput;
import vn.iotstar.dto.CategoryPage;
import vn.iotstar.entity.Category;

public interface ICategoryService {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    CategoryPage getCategoriesWithPaging(Integer page, Integer size, String search);

    Category createCategory(CategoryInput input);

    Category updateCategory(Long id, CategoryInput input);

    Boolean deleteCategory(Long id);
}
