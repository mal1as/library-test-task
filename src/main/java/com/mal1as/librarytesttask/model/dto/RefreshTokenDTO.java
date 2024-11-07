package com.mal1as.librarytesttask.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RefreshTokenDTO {

    @NotBlank
    private String refreshToken;
}
