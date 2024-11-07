package com.mal1as.librarytesttask.mapper;

import com.mal1as.librarytesttask.model.dto.AuthorDTO;
import com.mal1as.librarytesttask.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

    AuthorDTO toAuthorDTO(Author author);
}
