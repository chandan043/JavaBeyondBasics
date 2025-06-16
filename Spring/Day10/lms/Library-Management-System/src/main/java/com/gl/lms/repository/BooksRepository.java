package com.gl.lms.repository;

import com.gl.lms.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {
    //default methods
    //findAll
    //findById
    //find
    //DeleteAll
    //DeleteById
    //delete
    //save-> insert,update
    //to reduce all the db fetching stmts like connection,preparedstmt,resultset etc.

}
