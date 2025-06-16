package com.gl.lms.repository;

import com.gl.lms.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

    public Authors findByName(String name);
}
