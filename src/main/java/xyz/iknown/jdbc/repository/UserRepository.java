package xyz.iknown.jdbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.iknown.jdbc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    Boolean existsByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User set lastLoginTime =?1 where id=?2")
    void updateLastLoginTimeById(long lastLoginTime, long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User set password =?1 where id=?2")
    void updatePasswordById(String password,long id);

}
