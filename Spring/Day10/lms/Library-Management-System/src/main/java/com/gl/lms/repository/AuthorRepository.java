package com.gl.lms.repository;

import com.gl.lms.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authors,Integer > {
}
