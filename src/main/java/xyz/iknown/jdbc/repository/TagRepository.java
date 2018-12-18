package xyz.iknown.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.iknown.jdbc.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    boolean existsById(long id);
    Tag findById(long id);

    @Transactional
    @Modifying
    @Query("update Tag set tag=?1 where id=?2")
    void updateTagById(String newTag,long id);

    List<Tag> findAll();
}
