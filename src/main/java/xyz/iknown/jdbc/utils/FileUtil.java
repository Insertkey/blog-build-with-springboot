package xyz.iknown.jdbc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void deleteFile(String fullPath){
        File targetFile=new File(fullPath);
        if(targetFile.exists()&&targetFile.isFile()){
            targetFile.delete();
        }
    }
}
