package xyz.iknown.jdbc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.iknown.jdbc.entity.User;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() {
        int page=0;
        int size=1;
        Pageable pageable=new PageRequest(page,size);
        Page p=userRepository.findAll(pageable);
        System.out.println(p.getTotalElements());
        System.out.println(p.getTotalPages());
        List<User> userList=p.getContent();
        for (User user:userList){
            System.out.println(user);
        }
    }
}