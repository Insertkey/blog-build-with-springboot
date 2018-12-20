package xyz.iknown.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.iknown.jdbc.service.FileService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam Map<String,Object> stringObjectMap){
        return fileService.handelUploadFile(file,stringObjectMap);
    }

    @GetMapping("/list")
    public Map<String,Object> getArticleList(@RequestParam int page,@RequestParam int size){
        return fileService.getArticleList(page,size);
    }

    @DeleteMapping("/{id}")
    public Map<String,Object> deleteArticle(@PathVariable Integer id){
        return fileService.deleteFile(id);
    }

}
