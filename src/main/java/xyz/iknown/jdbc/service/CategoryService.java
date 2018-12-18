package xyz.iknown.jdbc.service;

import java.util.Map;

public interface CategoryService {
    Map<String,Object> addCategory(Map<String,Object> stringObjectMap);
    Map<String,Object> deleteCategory(String id);
    Map<String,Object> editCategory(Map<String,Object> stringObjectMap);
}
