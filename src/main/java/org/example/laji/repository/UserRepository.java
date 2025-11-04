package org.example.laji.repository;

import org.example.laji.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByWechatOpenid(String wechatOpenid);
    boolean existsByWechatOpenid(String wechatOpenid);
}

