package com.barexas.services.transactions;


import com.barexas.entities.Bill;
import com.barexas.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query("SELECT t FROM Transaction t where t.bill.owner.id =:id")
    List<Transaction> findByClientId(@Param("id") Long id);
}
