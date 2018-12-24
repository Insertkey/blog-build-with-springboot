package xyz.iknown.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.iknown.jdbc.entity.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Integer> {
    boolean existsByArticleName(String fileName);

    List<File> findByCategory_Id(Integer categoryId);
}
