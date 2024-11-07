package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.mapper.ClientMapper;
import com.mal1as.librarytesttask.model.dto.ClientDTO;
import com.mal1as.librarytesttask.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDTO findMostReading() {
        return clientMapper.toClientDTO(clientRepository.findMostReading());
    }

    public Page<ClientDTO> getAllByNonReturnCount(Pageable pageable) {
        return clientRepository.getAllByNonReturnCount(pageable).map(clientMapper::toClientDTO);
    }
}
