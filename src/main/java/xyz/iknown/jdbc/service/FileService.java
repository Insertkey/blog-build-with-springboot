package xyz.iknown.jdbc.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    Map<String,Object> handelUploadFile(MultipartFile file,Map<String,Object> stringObjectMap);
}
