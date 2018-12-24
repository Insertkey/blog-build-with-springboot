package xyz.iknown.jdbc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {
    public static Map<String,Object> faildResponse(String errMsg){
        Map<String,Object> modelMap=new HashMap<>();
        modelMap.put("success",false);
        modelMap.put("data",null);
        modelMap.put("errMsg",errMsg);
        return modelMap;
    }

    public static Map<String,Object> successResponse(List list){
        Map<String,Object> modelMap=new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("data",list);
        modelMap.put("errMsg","");
        return modelMap;
    }

    public static Map<String,Object> successResponseWithoutData(){
        Map<String,Object> modelMap=new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("data",null);
        modelMap.put("errMsg","");
        return modelMap;
    }

    public static Map<String,Object> paggingResponse(List list,Map<String,Object> map){
        Map<String,Object> modelMap=new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("errMsg","");
        modelMap.put("data",list);
        modelMap.put("option",map);
        return modelMap;
    }

    public static Map<String,Object> successResponseWithJson(Map<String,Object> map){
        Map<String,Object> modelMap=new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("errMsg","");
        modelMap.put("data",map);
        return modelMap;
    }
}
