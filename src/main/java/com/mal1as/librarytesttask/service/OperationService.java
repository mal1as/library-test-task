package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.model.entity.Book;
import com.mal1as.librarytesttask.model.entity.Client;
import com.mal1as.librarytesttask.model.entity.Operation;
import com.mal1as.librarytesttask.model.enums.OperationType;
import com.mal1as.librarytesttask.repository.BookRepository;
import com.mal1as.librarytesttask.repository.ClientRepository;
import com.mal1as.librarytesttask.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveOperation(Long clientId, Long bookId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        // add check that book now available

        Operation operation = operationRepository.findFirstByClientAndBookOrderByOperationDateDesc(client, book)
                .orElse(null);
        operationRepository.save(Operation.builder()
                .operationType(operation == null || operation.getOperationType().equals(OperationType.RETURN) ?
                        OperationType.TAKE : OperationType.RETURN)
                .client(client)
                .book(book)
                .build());
    }
}
