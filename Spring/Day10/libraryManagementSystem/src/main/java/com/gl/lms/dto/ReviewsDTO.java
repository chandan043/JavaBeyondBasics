package com.gl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewsDTO {

    private Integer id;

    private Integer rating;

    private String comment;

    private BooksDTO booksDTO;
}
