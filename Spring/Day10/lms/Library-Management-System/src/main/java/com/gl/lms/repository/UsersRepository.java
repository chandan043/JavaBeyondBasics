package com.gl.lms.repository;

import com.gl.lms.entity.users;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface UsersRepository extends JpaAttributeConverter<users,Integer> {
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
