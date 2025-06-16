package com.gl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UsersDTO {

    private Integer id;

    private String name;

    public String email;

    private LibraryCardsDTO libraryCardsDTO;
}
