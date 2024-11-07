package com.mal1as.librarytesttask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SuccessResponse<T> {

    @Builder.Default
    private Boolean success = true;
    private T content;
    @Builder.Default
    private Integer totalPages = 1;
    @Builder.Default
    private Long totalCount = 1L;
}