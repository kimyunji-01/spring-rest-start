package com.metacoding.springv2.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// findById, findAll, save, deleteById, count
public interface UserRepository extends JpaRepository<User, Integer> { // 인터페이스가 인터페이스를 상속할땐 extends를 사용

    @Query("select u from User u where u.username = :username")
    public Optional<User> findByUsername(@Param("username") String username);
}