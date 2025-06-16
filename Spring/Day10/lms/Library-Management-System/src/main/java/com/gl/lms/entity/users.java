package com.gl.lms.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  //creates the tables or the relations
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class users {

    @Id //adding this annotation indicate it as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //the id is automatically incremented
    private Integer id;
    @Column(name = "name",unique = true, nullable = false)
    private String name; //user_name will be generated
    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL) //used for one-to-one relation
    @JoinColumn(name = "Library_card_id", nullable = false)
    private LibraryCards libraryCards;
}
