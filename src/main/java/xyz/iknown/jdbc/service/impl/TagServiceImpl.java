package xyz.iknown.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iknown.jdbc.entity.Tag;
import xyz.iknown.jdbc.repository.TagRepository;
import xyz.iknown.jdbc.service.TagService;
import xyz.iknown.jdbc.utils.ResponseUtil;

import java.util.Map;

@Service("TagService")
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Map<String, Object> addTag(Map<String,Object> stringObjectMap) {
        Tag tag= new Tag();
        tag.setTag((String) stringObjectMap.get("tag"));
        tagRepository.save(tag);
        return ResponseUtil.successResponseWithoutData();
    }

    @Override
    public Map<String, Object> updateTag(Map<String,Object> stringObjectMap) {
        Integer id=(Integer) stringObjectMap.get("id");
        Tag tag=findATag(id);
        if (tag==null){
            return ResponseUtil.faildResponse("该标签不存在");
        }else {
            String newTag= (String) stringObjectMap.get("newTag");
            tagRepository.updateTagById(newTag,id);
            return ResponseUtil.successResponseWithoutData();
        }
    }

    private Tag findATag(int id){
        if(tagRepository.existsById(id)){
            return tagRepository.findById(id);
        }
        else return null;
    }
}
