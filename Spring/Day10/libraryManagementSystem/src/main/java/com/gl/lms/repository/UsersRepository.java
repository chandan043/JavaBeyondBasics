package com.gl.lms.repository;

import com.gl.lms.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    public Users findByEmail(String email);

    // findAll
    //findById
    //find
    //deleteByTitle
    //deleteAll
    //deleteById
    // save -> insert, update
    // findByNameAndEmail

    //    @Query("SELECT u FROM Users u WHERE email = :email")
//    public Users fetchUsersByEmail(String email);

    // IF we are trying to get username, email
    // @Query("SELECT u.name, u.email FROM Users u WHERE email = :email")

//    @Query("SELECT u FROM Users u WHERE email = ?1")
//    public Users fetchUsersByEmail(String email);

    // For all the SELECT operation(First way)(Best practices)
    //public Users findByEmail(String email);

    // For all the SELECT operation (Second Way)
    // public Users getByEmail(String email);

    // Write a query to find users  by name and email
//    @Query("SELECT u FROM Users u WHERE email =: email and name = :name")
//    public Users fetchUsersByEmailAndName(String email, String name);

    // This is also fine
//    @Query("select u from users u where email=?1 and name=?2")
//    Public users fetchusersbyEmailName(String email,String name);
//
//     Method Way
//    public Users findByEmailAndName(String email, String name);


    // Write a query to find users  by name or email
//    @Query("SELECT u FROM Users u WHERE email =: email or name = :name")
//    public Users fetchUsersByEmailORName(String email, String name);

    // Method Way
//    public Users findByEmailOrName(String email, String name);

//    @Query("UPDATE Users u SET u.name = :name WHERE u.email = :email")
//    @Modifying
//    @Transactional
//    public void updateUserName(String email);

}
