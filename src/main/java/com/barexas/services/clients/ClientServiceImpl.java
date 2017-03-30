package com.barexas.services.clients;

import com.barexas.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    @Transactional
    public Client addClient(String email) {
        return clientsRepository.save(new Client(email));
    }

    @Override
    public List<Client> getAll() {
        return clientsRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientsRepository.findOne(id);
    }

    @Override
    public Client getByEmail(String email) {
        return clientsRepository.findByClientEmail(email);
    }
}
