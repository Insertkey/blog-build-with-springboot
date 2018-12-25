package xyz.iknown.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import java.util.*;

@Service("FileService")
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private String path = "C:/static/markdownFile/";

    @Override
    public Map<String, Object> handelUploadFile(MultipartFile file, Map<String, Object> stringObjectMap) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return ResponseUtil.faildResponse("文件为空");
        }
        try {
            file.transferTo(new java.io.File(path + file.getOriginalFilename()));
            System.out.println(path);
            try {
                // 判断文章分类是否存在
                if (categoryRepository.existsByCategoryName((String) stringObjectMap.get("category"))) {
                    try {
                        String fullName = file.getOriginalFilename();
                        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
                        // 判断文章是否存在
                        if (!fileRepository.existsByArticleName(fileName)) {
                            File file1 = new File();
                            Category category = categoryRepository.findByCategoryName((String) stringObjectMap.get("category"));
                            file1.setCategory(category);
                            file1.setArticleName(fileName);
                            file1.setFullPath(path + file.getOriginalFilename());
                            Date date = new Date();
                            file1.setCreateTime(date.getTime());
                            file1.setLastEditTime(date.getTime());
                            file1.setShortIntroduction((String) stringObjectMap.get("shortIntroduction"));
                            fileRepository.save(file1);
                            return ResponseUtil.successResponseWithoutData();
                        } else {
                            return ResponseUtil.faildResponse("该文章已存在");
                        }
                    } catch (Exception e) {
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

    @Override
    public Map<String, Object> editFile(Map<String, Object> stringObjectMap) {
        Integer id = (Integer) stringObjectMap.get("id");
        if (fileRepository.existsById(id)) {
            try {
                File file = new File();
                Category category = categoryRepository.findByCategoryName((String) stringObjectMap.get("categoryName"));
                file.setId(id);
                file.setCategory(category);
                Date date = new Date();
                file.setLastEditTime(date.getTime());
                file.setShortIntroduction((String) stringObjectMap.get("shortIntroduction"));
                fileRepository.save(file);
                return ResponseUtil.successResponseWithoutData();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseUtil.faildResponse("更新文章失败，请稍后再试");
            }
        } else {
            return ResponseUtil.faildResponse("该文章不存在");
        }
    }

    @Override
    public Map<String, Object> deleteFile(Integer id) {
        try {
            deleteFileFormDisk(id);
            fileRepository.deleteById(id);
            return ResponseUtil.successResponseWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.faildResponse("删除失败，请稍后再试");
        }
    }

    /**
     * 只有分页，不带排序
     *
     * @param page 从零开始
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> getArticleList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page p = fileRepository.findAll(pageable);
        List<File> fileList = p.getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("total", p.getTotalElements());
        map.put("page", p.getTotalPages());
        return ResponseUtil.paggingResponse(fileList, map);
    }

    /**
     * 有分页和排序
     *
     * @param size      每页数量
     * @param page      页数
     * @param sortKey   排序字段
     * @param sortValue 排序方式
     * @return
     */
    @Override
    public Map<String, Object> getArticleList(int page, int size, String sortKey, String sortValue) {
        if (sortKey.equals("undefined") || sortKey.equals("null")) {
            return getArticleList(page, size);
        } else {
            if (sortValue.equals("descend")) {
                Sort sort = new Sort(Sort.Direction.DESC, sortKey);
                Page p = fileRepository.findAll(PageRequest.of(page - 1, size, sort));
                List<File> fileList = p.getContent();
                Map<String, Object> map = new HashMap<>();
                map.put("total", p.getTotalElements());
                map.put("page", p.getTotalPages());
                return ResponseUtil.paggingResponse(fileList, map);
            } else {
                Sort sort = new Sort(Sort.Direction.ASC, sortKey);
                Page p = fileRepository.findAll(PageRequest.of(page - 1, size, sort));
                List<File> fileList = p.getContent();
                Map<String, Object> map = new HashMap<>();
                map.put("total", p.getTotalElements());
                map.put("page", p.getTotalPages());
                return ResponseUtil.paggingResponse(fileList, map);
            }
        }
    }

    @Override
    public Map<String, Object> getArticleInfo(Integer id) {
        File file = fileRepository.getOne(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", file.getId());
        map.put("category", file.getCategory().getCategoryName());
        map.put("shortIntroduction", file.getShortIntroduction());
        return ResponseUtil.successResponseWithJson(map);
    }

    @Override
    public Map<String, Object> getArticleListWithCategory(Integer categoryId) {
        List<File> fileList = fileRepository.findByCategory_Id(categoryId);
        return ResponseUtil.successResponse(fileList);
    }

    @Override
    public Map<String, Object> updateArticle(MultipartFile updateFile, Map<String, Object> stringObjectMap) {
        if (updateFile.isEmpty()) {
            return ResponseUtil.faildResponse("文件为空");
        } else {
            try {
                updateFile.transferTo(new java.io.File(path + updateFile.getOriginalFilename()));
                Integer id = Integer.parseInt((String) stringObjectMap.get("id"));
                File file = fileRepository.getOne(id);
                Category category = categoryRepository.findByCategoryName((String) stringObjectMap.get("categoryName"));
                file.setCategory(category);
                Date date = new Date();
                file.setLastEditTime(date.getTime());
                file.setShortIntroduction((String) stringObjectMap.get("shortIntroduction"));
                fileRepository.save(file);
                return ResponseUtil.successResponseWithoutData();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseUtil.faildResponse("更新文章失败，请稍后再试");
            }
        }

    }

    private void deleteFileFormDisk(Integer id) {
        File file = fileRepository.getOne(id);
        String fullPath = file.getFullPath();
        FileUtil.deleteFile(fullPath);
    }
}
