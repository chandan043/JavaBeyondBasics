package com.gl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class LibraryCardsDTO {

    private Integer id;

    private LocalDate issueDate;

    private LocalDate expiryDate;
}
