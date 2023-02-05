package com.gl.student.repository;

import com.gl.student.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}