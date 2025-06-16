package com.gl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="authors")
@Setter
@Getter
@NoArgsConstructor
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Integer id;

    @Column(name="author_name",nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id",nullable = false)
    private List<Books> books;
}
