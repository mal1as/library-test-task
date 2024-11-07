package com.mal1as.librarytesttask.mapper;

import com.mal1as.librarytesttask.model.dto.ClientDTO;
import com.mal1as.librarytesttask.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDTO toClientDTO(Client client);
}
