package com.mal1as.librarytesttask.repository;

import com.mal1as.librarytesttask.model.entity.Book;
import com.mal1as.librarytesttask.model.entity.Client;
import com.mal1as.librarytesttask.model.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Optional<Operation> findFirstByClientAndBookOrderByOperationDateDesc(Client client, Book book);
}
