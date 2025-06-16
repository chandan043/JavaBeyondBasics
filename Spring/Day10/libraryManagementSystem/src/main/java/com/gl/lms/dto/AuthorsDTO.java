package com.gl.lms.dto;

import com.gl.lms.entity.Books;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AuthorsDTO {

    private Integer id;

    private String name;

    private List<Books> books;
}
