package xyz.iknown.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iknown.jdbc.entity.Category;
import xyz.iknown.jdbc.repository.CategoryRepository;
import xyz.iknown.jdbc.service.CategoryService;
import xyz.iknown.jdbc.utils.ResponseUtil;

import java.util.Date;
import java.util.Map;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Map<String, Object> addCategory(Map<String, Object> stringObjectMap) {
        Category category = new Category();
        category.setCategoryName((String) stringObjectMap.get("categoryName"));
        Date date = new Date();
        category.setLastEditTime(date.getTime());
        try {
            categoryRepository.save(category);
            return ResponseUtil.successResponseWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.faildResponse("添加分类失败，请稍后再试");
        }
    }

    @Override
    public Map<String, Object> deleteCategory(String id) {
        try {
            categoryRepository.deleteById(Integer.parseInt(id));
            return ResponseUtil.successResponseWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.faildResponse("删除分类失败，请稍后再试");
        }
    }

    @Override
    public Map<String, Object> editCategory(Map<String, Object> stringObjectMap) {
        String categoryName=stringObjectMap.get("categoryName").toString();
        Integer id=(Integer) stringObjectMap.get("id");
        try {
            categoryRepository.updateCategoryNameById(categoryName,id);
            return ResponseUtil.successResponseWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.faildResponse("修改分类失败，请稍后再试");
        }
    }

    @Override
    public Map<String, Object> getCategoryList() {
        return ResponseUtil.successResponse(categoryRepository.findAll());
    }
}
