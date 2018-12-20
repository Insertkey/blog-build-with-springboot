package xyz.iknown.jdbc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void fileupload(byte[] file, String filePath, String fileName) throws IOException {
        //目标目录		
        File targetFile = new File(filePath);
        if (targetFile.exists()) {
            targetFile.mkdirs();
        }

        //二进制流写入		
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static void deleteFile(String fullPath){
        File targetFile=new File(fullPath);
        if(targetFile.exists()&&targetFile.isFile()){
            targetFile.delete();
        }
    }
}
