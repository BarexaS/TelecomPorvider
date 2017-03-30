package com.barexas.services.clients;

import com.barexas.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c where c.email = :email")
    Client findByClientEmail(@Param("email") String email);
}
