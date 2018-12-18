package xyz.iknown.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.iknown.jdbc.service.CategoryService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Map<String,Object> addCategory(@RequestBody Map<String,Object> stringObjectMap){
        return categoryService.addCategory(stringObjectMap);
    }

    @PutMapping("/edit")
    public Map<String,Object> editCategory(@RequestBody Map<String,Object> stringObjectMap){
        return categoryService.editCategory(stringObjectMap);
    }

    @DeleteMapping("/{id}")
    public Map<String,Object> deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/list")
    public Map<String,Object> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
