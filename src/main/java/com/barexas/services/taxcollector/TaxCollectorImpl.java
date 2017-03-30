package com.barexas.services.taxcollector;

import com.barexas.entities.Transaction;
import com.barexas.services.bills.BillService;
import com.barexas.services.mail.MailService;
import com.barexas.services.transactions.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TaxCollectorImpl implements TaxCollector {
    @Autowired
    private BillService billService;

    @Autowired
    private MailService mailService;

    private static String MAIL_SUBJECT = "Outstanding balance";
    private static String MAIL_TEXT = "You have outstanding balance";

    @Override
    @Async
    public void collectTax() {
        LocalDateTime now = LocalDateTime.now();
        long period = now.until(now.plusSeconds(5), ChronoUnit.MILLIS);
        while (!Thread.interrupted()) {
            collecting();
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    private void collecting() {
        System.out.println("Collecting!");
        try {
            billService.getAll()
                    .forEach(bill -> {
                        BigDecimal tax = bill.getPriceModel().getTax();
                        System.out.println(tax);
                        bill.setBalance(bill.getBalance().subtract(tax));
                        Transaction transaction = new Transaction(bill, tax.negate(), LocalDateTime.now());
                        bill.addTransaction(transaction);
                        if (bill.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
                            mailService.sendMail(
                                    mailService.buildMail(bill.getOwner().getEmail(), MAIL_SUBJECT, MAIL_TEXT)
                            );
                        }
                        billService.updateBill(bill);
                    });
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
