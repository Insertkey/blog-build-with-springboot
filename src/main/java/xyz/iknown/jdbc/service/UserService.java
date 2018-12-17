package xyz.iknown.jdbc.service;


import java.util.Map;


public interface UserService {
    Map<String,Object> handelLogin(Map<String,Object> stringObjectMap);
    Map<String,Object> changePassword(Map<String,Object> stringObjectMap);
}
