package com.devsuperior.clientes.repositories;

import com.devsuperior.clientes.entities.Client;
import com.devsuperior.clientes.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query( "SELECT client FROM Client client" )
    Page<Client> find( List<Client> clients, String name, Pageable pageable );
}