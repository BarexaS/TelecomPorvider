package com.barexas.services.transactions;

import com.barexas.entities.Bill;
import com.barexas.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findOne(id);
    }

    @Override
    public List<Transaction> getByClientId(Long id) {
        return transactionRepository.findByClientId(id);
    }

    @Override
    public List<Transaction> getByDateRange(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getTime().isAfter(start))
                .filter(transaction -> transaction.getTime().isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getByDateRangeAndClientId(LocalDateTime start, LocalDateTime end, Long id) {
        return transactionRepository.findByClientId(id).stream()
                .filter(transaction -> transaction.getTime().isAfter(start))
                .filter(transaction -> transaction.getTime().isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Transaction addTransaction(Bill bill, BigDecimal amount, LocalDateTime time) {
        return transactionRepository.save(new Transaction(bill,amount,time));
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
