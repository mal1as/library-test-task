package com.mal1as.librarytesttask.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
