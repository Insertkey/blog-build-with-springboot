package xyz.iknown.jdbc.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileService {
    Map<String, Object> handelUploadFile(MultipartFile file, Map<String, Object> stringObjectMap);

    Map<String, Object> editFile(Map<String, Object> stringObjectMap);

    Map<String,Object> deleteFile(Integer id);

    Map<String,Object> getArticleList(int size,int page);

    Map<String,Object> getArticleList(int size,int page,String sortKey,String sortValue);

    Map<String, Object> getArticleInfo(Integer id);

    Map<String,Object> getArticleListWithCategory(Integer categoryId);

    Map<String,Object> updateArticle(MultipartFile file,Map<String,Object> stringObjectMap) throws IOException;
}
