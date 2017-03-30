package com.barexas.services.transactions;

import com.barexas.entities.Bill;
import com.barexas.entities.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAll();
    Transaction getById(Long id);
    List<Transaction> getByClientId(Long id);
    List<Transaction> getByDateRange(LocalDateTime start, LocalDateTime end);
    Transaction addTransaction(Bill bill, BigDecimal amount, LocalDateTime time);
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getByDateRangeAndClientId(LocalDateTime start, LocalDateTime end, Long id);
}
