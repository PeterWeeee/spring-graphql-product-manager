package vn.iotstar.controller.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.iotstar.dto.CategoryInput;
import vn.iotstar.dto.CategoryPage;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;

@Controller
public class CategoryGraphQLController {

    @Autowired
    private ICategoryService categoryService;

    @QueryMapping
    public CategoryPage categories(@Argument Integer page, @Argument Integer size, @Argument String search) {
        return categoryService.getCategoriesWithPaging(page, size, search);
    }

    @QueryMapping
    public List<Category> allCategories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryService.findById(id).orElse(null);
    }

    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        return categoryService.createCategory(input);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        return categoryService.updateCategory(id, input);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.deleteCategory(id);
    }
}
