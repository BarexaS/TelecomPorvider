package com.barexas.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Client owner;
    private BigDecimal balance;
    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "pricemodel_id")
    private PriceModel priceModel;
    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new LinkedList<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public Bill() {
    }

    public Bill(Client owner) {
        this.owner = owner;
        this.balance = new BigDecimal(0.0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(PriceModel priceModel) {
        this.priceModel = priceModel;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
