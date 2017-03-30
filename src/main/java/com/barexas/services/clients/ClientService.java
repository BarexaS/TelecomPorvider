package com.barexas.services.clients;


import com.barexas.entities.Client;

import java.util.List;

public interface ClientService {
    Client addClient(String email);
    List<Client> getAll();
    Client getById(Long id);
    Client getByEmail(String email);
}
