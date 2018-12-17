package xyz.iknown.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iknown.jdbc.entity.User;
import xyz.iknown.jdbc.repository.UserRepository;
import xyz.iknown.jdbc.service.UserService;
import xyz.iknown.jdbc.utils.ResponseUtil;

import java.util.Date;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Map<String,Object> handelLogin(Map<String,Object> loggingUser){
        User user= findAUser((String) loggingUser.get("userName"));
        if(user==null){
            return ResponseUtil.faildResponse("该用户不存在");
        }else {
            if(user.getPassword().equals(loggingUser.get("password"))){
                setLastLoginTime(user);
                return ResponseUtil.successResponseWithoutData();
            }else {
                return ResponseUtil.faildResponse("密码错误");
            }
        }
    }

    @Override
    public Map<String, Object> changePassword(Map<String, Object> stringObjectMap) {
        User user =findAUser((String) stringObjectMap.get("userName"));
        if(user==null){
            return ResponseUtil.faildResponse("该用户不存在");
        }else {
            if(user.getPassword().equals(stringObjectMap.get("oldPassword"))){
                userRepository.updatePasswordById((String) stringObjectMap.get("newPassword"),user.getId());
                return ResponseUtil.successResponseWithoutData();
            }else {
                return ResponseUtil.faildResponse("密码错误");
            }
        }
    }

    private User findAUser(String name) {
        if(userRepository.existsByName(name)){
            return userRepository.findByName(name);
        }
        else {
            return null;
        }
    }

    private void setLastLoginTime(User user){
        Date date=new Date();
        userRepository.updateLastLoginTimeById(date.getTime(),user.getId());
    }
}
