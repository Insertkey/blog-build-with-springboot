package xyz.iknown.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.iknown.jdbc.entity.Category;
import xyz.iknown.jdbc.entity.File;
import xyz.iknown.jdbc.repository.CategoryRepository;
import xyz.iknown.jdbc.repository.FileRepository;
import xyz.iknown.jdbc.service.FileService;
import xyz.iknown.jdbc.utils.FileUtil;
import xyz.iknown.jdbc.utils.ResponseUtil;

import java.util.Date;
import java.util.Map;

@Service("FileService")
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Map<String, Object> handelUploadFile(MultipartFile file, Map<String, Object> stringObjectMap) {
        if (file == null) {
            return ResponseUtil.faildResponse("文件为空");
        } else {
            if (file.isEmpty()) {
                return ResponseUtil.faildResponse("文件为空");
            }
            try {
                String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/markdownFile/";
                FileUtil.fileupload(file.getBytes(), path, file.getOriginalFilename());
                try {
                    if (categoryRepository.existsByCategoryName((String) stringObjectMap.get("category"))) {
                        try {
                            File file1 = new File();
                            Category category = categoryRepository.findByCategoryName((String) stringObjectMap.get("category"));
                            file1.setCategory(category);
                            file1.setArticleName(file.getOriginalFilename());
                            file1.setFullPath(path + file.getOriginalFilename());
                            Date date = new Date();
                            file1.setCreateTime(date.getTime());
                            file1.setLastEditTime(date.getTime());
                            fileRepository.save(file1);
                            return ResponseUtil.successResponseWithoutData();
                        }catch (Exception e){
                            e.printStackTrace();
                            return ResponseUtil.faildResponse("上传失败，请稍后再试");
                        }
                    } else {
                        return ResponseUtil.faildResponse("该标签不存在");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseUtil.faildResponse("上传失败，请稍后再试");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseUtil.faildResponse("上传失败，请稍后再试");
            }
        }
    }
}
