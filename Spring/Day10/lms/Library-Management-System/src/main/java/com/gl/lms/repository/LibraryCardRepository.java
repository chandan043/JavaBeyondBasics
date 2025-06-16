package com.gl.lms.repository;

import com.gl.lms.entity.LibraryCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<LibraryCards, Integer> {
}
