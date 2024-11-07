package com.mal1as.librarytesttask.repository;

import com.mal1as.librarytesttask.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = "select c.* from client c " +
            "join operation o on c.id = o.client_id " +
            "where o.operation_type = 'TAKE' " +
            "group by c.id, c.last_name " +
            "order by count(c.id) desc, c.last_name " +
            "limit 1")
    Client findMostReading();

    @Query(nativeQuery = true, value = "select c.* from client c " +
            "left join operation o on c.id = o.client_id " +
            "group by c.id, c.last_name " +
            "order by count(case when o.operation_type = 'TAKE' then 1 end) - " +
            "count(case when o.operation_type = 'RETURN' then 1 end) desc, c.last_name",
            countQuery = "select count(c.id) from client c")
    Page<Client> getAllByNonReturnCount(Pageable pageable);
}
