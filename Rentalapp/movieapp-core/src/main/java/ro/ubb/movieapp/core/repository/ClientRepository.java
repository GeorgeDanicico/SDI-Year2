package ro.ubb.movieapp.core.repository;

import org.springframework.stereotype.Repository;
import ro.ubb.movieapp.core.model.Client;

@Repository
public interface ClientRepository extends IRepository<Client, Long>{
}
