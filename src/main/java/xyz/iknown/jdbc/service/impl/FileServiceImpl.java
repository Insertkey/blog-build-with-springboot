package xyz.iknown.jdbc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.iknown.jdbc.service.FileService;
import xyz.iknown.jdbc.utils.FileUtil;
import xyz.iknown.jdbc.utils.ResponseUtil;

import java.util.Map;

@Service("FileService")
public class FileServiceImpl implements FileService {

    @Override
    public Map<String, Object> handelUploadFile(MultipartFile file) {
        if(file==null){
            return ResponseUtil.faildResponse("文件为空");
        }else {
            System.out.println(file.getOriginalFilename());
            if(file.isEmpty()){
                return ResponseUtil.faildResponse("文件为空");
            }
            try {
                String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/markdownFile/";
                System.out.println(path);
                FileUtil.fileupload(file.getBytes(),path,file.getOriginalFilename());
                return ResponseUtil.successResponseWithoutData();
            }catch (Exception e){
                return ResponseUtil.faildResponse("上传失败，请稍后再试");
            }
        }
    }
}
