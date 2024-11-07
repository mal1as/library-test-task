package com.mal1as.librarytesttask.model.dto;

import com.mal1as.librarytesttask.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientDTO {

    private Long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
}
