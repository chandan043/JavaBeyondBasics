package com.gl.lms.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsDTO {

    private Integer id;

    private String rating;

    private String comment;

    private BooksDTO booksDTO;
}
