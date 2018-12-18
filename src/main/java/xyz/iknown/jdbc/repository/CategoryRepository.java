package xyz.iknown.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.iknown.jdbc.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Category set categoryName =?1 where id=?2")
    void updateCategoryNameById(String categoryName,Integer id);

    Category findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
}
