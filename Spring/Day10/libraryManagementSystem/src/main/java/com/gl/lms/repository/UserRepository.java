package com.gl.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.lms.entity.Books;
import com.gl.lms.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{

}
