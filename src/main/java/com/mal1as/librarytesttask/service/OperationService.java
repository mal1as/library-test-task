package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.model.entity.Book;
import com.mal1as.librarytesttask.model.entity.Client;
import com.mal1as.librarytesttask.model.entity.Operation;
import com.mal1as.librarytesttask.model.enums.OperationType;
import com.mal1as.librarytesttask.repository.BookRepository;
import com.mal1as.librarytesttask.repository.ClientRepository;
import com.mal1as.librarytesttask.repository.OperationRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mal1as.librarytesttask.utils.ErrorMessages.BOOK_NOT_AVAILABLE_MESSAGE;
import static com.mal1as.librarytesttask.utils.ErrorMessages.BOOK_NOT_FOUND_MESSAGE;
import static com.mal1as.librarytesttask.utils.ErrorMessages.CLIENT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveOperation(Long clientId, Long bookId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ValidationException(String.format(CLIENT_NOT_FOUND_MESSAGE, clientId)));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ValidationException(String.format(BOOK_NOT_FOUND_MESSAGE, bookId)));


        Operation lastBookOperation = operationRepository.findFirstByBookOrderByOperationDateDesc(book)
                .orElse(null);
        if (lastBookOperation != null && lastBookOperation.getOperationType().equals(OperationType.TAKE)
                && !lastBookOperation.getClient().getId().equals(clientId)) {
            throw new ValidationException(String.format(BOOK_NOT_AVAILABLE_MESSAGE, bookId));
        }

        Operation lastOperation = operationRepository.findFirstByClientAndBookOrderByOperationDateDesc(client, book)
                .orElse(null);
        operationRepository.save(Operation.builder()
                .operationType(lastOperation == null || lastOperation.getOperationType().equals(OperationType.RETURN) ?
                        OperationType.TAKE : OperationType.RETURN)
                .client(client)
                .book(book)
                .build());
    }
}
