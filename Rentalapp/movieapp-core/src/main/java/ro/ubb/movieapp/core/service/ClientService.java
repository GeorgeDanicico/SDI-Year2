package ro.ubb.movieapp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.movieapp.core.model.Client;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void uploadData(List<Client> clients) {
        clientRepository.saveAll(clients);
    }
}
