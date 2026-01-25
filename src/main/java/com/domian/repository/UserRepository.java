package com.domian.repository;

import com.domian.entity.LoginInfo;
import com.domian.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    @Query(value = "SELECT count(1) FROM UserInfo")
    long countInfTestCase();

    UserInfo findById(int id);

    @Query(value = "SELECT max(o.id) FROM UserInfo o")
    long selectMaxId();

    @Query(value = "SELECT o FROM UserInfo o WHERE o.username = :username ORDER BY o.id DESC")
    List<UserInfo> findByUser(@Param("username") String username);

    @Modifying
    @Query(value = "INSERT INTO login_table (username, password) VALUES ( :username, :password)",nativeQuery = true)
    void insertByUser(@Param("username") String username, @Param("password") String password);
}
