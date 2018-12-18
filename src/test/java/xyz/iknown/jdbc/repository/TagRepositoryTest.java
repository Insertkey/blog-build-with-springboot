package xyz.iknown.jdbc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.iknown.jdbc.entity.Tag;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    public void findAll() {
        Tag tag =tagRepository.findById(2);
        System.out.println(tag);
    }
}