package com.gl.lms.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {

    private Integer id;

    private String name;

    private String email;

    private LibraryCardsDTO librarycards;
}
