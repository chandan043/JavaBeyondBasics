package com.gl.lms.DTO;

import com.gl.lms.entity.Books;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorsDTO {

    private Integer id;

    private String name;

    private List<Books> books;
}

