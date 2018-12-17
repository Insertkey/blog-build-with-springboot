package xyz.iknown.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.iknown.jdbc.service.UserService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> stringObjectMap) {
        return userService.handelLogin(stringObjectMap);
    }

    @PutMapping("/change")
    public Map<String,Object> changePassword(@RequestBody Map<String,Object> stringObjectMap){
        return userService.changePassword(stringObjectMap);
    }

}
