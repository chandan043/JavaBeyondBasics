package com.gl.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="reviews")
@Setter
@Getter
@NoArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Integer id;

    @Column(name="rating")
    private Integer rating;

    @Column(name="comment")
    private String comment;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="book_id")
    private Books book;
}
