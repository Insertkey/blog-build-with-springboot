package xyz.iknown.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.iknown.jdbc.entity.File;

public interface FileRepository extends JpaRepository<File,Integer> {
}
