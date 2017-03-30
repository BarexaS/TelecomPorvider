package com.barexas.controllers;

import com.barexas.entities.Bill;
import com.barexas.entities.Client;
import com.barexas.services.bills.BillService;
import com.barexas.services.clients.ClientService;
import com.barexas.services.pricemodels.PriceModelService;
import com.barexas.services.transactions.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Controller
public class MainController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private PriceModelService priceModelService;
    @Autowired
    private BillService billService;
    @Autowired
    private TransactionService transactionService;


    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("home");
        model.addObject("clients", clientService.getAll());
        model.addObject("pricemodel", priceModelService.getAll());
        return model;
    }
    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ModelAndView addClient(@RequestParam("email") String email, @RequestParam("pricemodel") Long modelId){
        if (!email.isEmpty() && !email.equalsIgnoreCase("")) {
            Client client = clientService.addClient(email);
            Bill bill = billService.addBill(client);
            bill.setPriceModel(priceModelService.getById(modelId));
            billService.updateBill(bill);
        }
        return home();
    }

    @RequestMapping("/view_{client_id}")
    public ModelAndView viewClient(@PathVariable("client_id") Long id){
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("client", clientService.getById(id));
        Bill bill = billService.getByClientId(id);
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("priceModel", priceModelService.getAll());
        modelAndView.addObject("billModel", bill.getPriceModel().getName());
        return modelAndView;
    }

    @RequestMapping(value = "/updateClient_{client_id}", method = RequestMethod.POST)
    public ModelAndView addClient(@PathVariable("client_id") Long id, @RequestParam("pricemodel") Long modelId){
        Bill bill = billService.getByClientId(id);
        bill.setPriceModel(priceModelService.getById(modelId));
        billService.updateBill(bill);
        return viewClient(id);
    }

    @RequestMapping("/transaction")
    public ModelAndView transaction(){
        ModelAndView model = new ModelAndView("transaction");
        model.addObject("transaction", transactionService.getAll());
        return model;
    }


//    2017-03-30T12:12
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request){
        ModelAndView model = new ModelAndView("transaction");
        String pattern = request.getParameter("pattern");
        Client client = clientService.getByEmail(pattern);
        try {
            model.addObject("pattern", pattern);
            LocalDateTime dateFrom = LocalDateTime.parse(request.getParameter("dateFrom"));
            LocalDateTime dateTill = LocalDateTime.parse(request.getParameter("dateTill"));
            model.addObject("dateFrom", dateFrom);
            model.addObject("dateTill", dateTill);
            if (client != null ) {
                model.addObject("transaction", transactionService.getByDateRangeAndClientId(dateFrom, dateTill, client.getId()));
            } else {
                model.addObject("transaction", transactionService.getByDateRange(dateFrom,dateTill));
            }
        } catch (DateTimeParseException exception){
            if (client != null ) {
                model.addObject("transaction", transactionService.getByClientId(client.getId()));
            }
        }

        return model;
    }
}
