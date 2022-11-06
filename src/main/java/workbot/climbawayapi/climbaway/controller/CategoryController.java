package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.CategoryService;
import workbot.climbawayapi.climbaway.mapping.CategoryMapper;
import workbot.climbawayapi.climbaway.resource.CategoryResource;
import workbot.climbawayapi.climbaway.resource.SaveCategoryResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryResource> getAllCategories(){
        return categoryMapper.toResource(categoryService.getAll());
    }

    @GetMapping(value = "/{id}")
    public CategoryResource findCategoryById(@PathVariable Long id){
        return categoryMapper.toResource(categoryService.findById(id));
    }

    @GetMapping(value = "/climbing-gyms/{id}")
    public List<CategoryResource> findCategoriesByClimbingGymId(@PathVariable Long id){
        return categoryMapper.toResource(categoryService.findByClimbingGymId(id));
    }

    @PostMapping
    public CategoryResource createCategory(@RequestBody SaveCategoryResource resource){
        return categoryMapper.toResource(categoryService.create(categoryMapper.toModelSaveResource(resource)));
    }

    @PutMapping(value = "/{id}")
    public CategoryResource updateCategory(@PathVariable Long id, @RequestBody SaveCategoryResource resource){
        return categoryMapper.toResource(categoryService.update(id, categoryMapper.toModelSaveResource(resource)));
    }

    @DeleteMapping(value = "/{id}")
    public CategoryResource deleteCategory(@PathVariable Long id){
        return categoryMapper.toResource(categoryService.delete(id));
    }

}
