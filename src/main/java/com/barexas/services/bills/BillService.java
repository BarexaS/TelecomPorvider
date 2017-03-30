package com.barexas.services.bills;


import com.barexas.entities.Bill;
import com.barexas.entities.Client;

import java.util.List;

public interface BillService {
    Bill getByClientId(Long id);
    Bill addBill(Client client);
    Bill updateBill(Bill bill);
    List<Bill> getAll();

}
