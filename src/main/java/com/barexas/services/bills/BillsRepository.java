package com.barexas.services.bills;

import com.barexas.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepository extends JpaRepository<Bill, Long>{
    @Query("SELECT b FROM Bill b where b.owner.id = :id")
    Bill findByClientId(@Param("id") Long id);
}
