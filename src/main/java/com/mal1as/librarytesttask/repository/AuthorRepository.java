package com.mal1as.librarytesttask.repository;

import com.mal1as.librarytesttask.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = "select a.* from author a " +
            "join book_author ba on a.id = ba.author_id " +
            "join book b on ba.book_id = b.id " +
            "join operation o on b.id = o.book_id " +
            "where o.operation_type = 'TAKE' and o.operation_date >= :from and o.operation_date <= :to " +
            "group by a.id, a.last_name " +
            "order by count(a.id) desc, a.last_name limit 1")
    Author findMostPopularBetween(LocalDate from, LocalDate to);
}
