package xyz.iknown.jdbc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.iknown.jdbc.entity.File;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @Test
    public void getFileList(){
        List<File> fileList= fileRepository.findAll();
        System.out.println(fileList);
    }
}