package com.gl.lms.repository;

import com.gl.lms.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {

    Books findByTitle(String bookTitle);
}
