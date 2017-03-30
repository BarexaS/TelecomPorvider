package com.barexas.services.bills;

import com.barexas.entities.Bill;
import com.barexas.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillsRepository billsRepository;

    @Override
    public Bill getByClientId(Long id){
        return billsRepository.findByClientId(id);
    }

    @Override
    @Transactional
    public Bill addBill(Client client) {
        return billsRepository.save(new Bill(client));
    }

    @Override
    @Transactional
    public Bill updateBill(Bill bill) {
        return billsRepository.saveAndFlush(bill);
    }

    @Override
    public List<Bill> getAll() {
        return billsRepository.findAll();
    }
}
