package xyz.iknown.jdbc.service;

import java.util.Map;

public interface TagService {
    Map<String,Object> addTag(Map<String,Object> stringObjectMap);
    Map<String,Object> updateTag(Map<String,Object> stringObjectMap);
    Map<String,Object> findAllTags();
    Map<String,Object> deleteTag(String id);
}
