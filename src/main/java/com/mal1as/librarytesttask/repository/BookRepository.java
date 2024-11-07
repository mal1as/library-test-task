package com.mal1as.librarytesttask.repository;

import com.mal1as.librarytesttask.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
