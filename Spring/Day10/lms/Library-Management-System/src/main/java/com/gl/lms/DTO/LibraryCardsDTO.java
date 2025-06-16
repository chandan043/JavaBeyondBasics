package com.gl.lms.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LibraryCardsDTO {

    private Integer id;

    private LocalDate issueDate;

    private LocalDate expiryDate;


}
