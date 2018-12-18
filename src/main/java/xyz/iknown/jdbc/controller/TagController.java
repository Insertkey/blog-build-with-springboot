package xyz.iknown.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.iknown.jdbc.service.TagService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("add")
    public Map<String,Object> addTag(@RequestBody Map<String,Object> stringObjectMap){
        return tagService.addTag(stringObjectMap);
    }

    @PutMapping("update")
    public Map<String,Object> updateTag(@RequestBody Map<String,Object> stringObjectMap){
        return tagService.updateTag(stringObjectMap);
    }

    @GetMapping("all")
    public Map<String,Object> findAllTags(){
        return tagService.findAllTags();
    }

    @DeleteMapping("/{id}")
    public Map<String,Object> deleteTag(@PathVariable String id){
        return tagService.deleteTag(id);
    }
}
