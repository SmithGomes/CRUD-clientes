package com.devsuperior.clientes.services;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.devsuperior.clientes.resources.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devsuperior.clientes.dto.ClientDTO;
import com.devsuperior.clientes.entities.Client;
import com.devsuperior.clientes.repositories.ClientRepository;
import com.devsuperior.clientes.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
    private static final long serialVersion = 1L;

    @Autowired
    private ClientRepository categoryRepository;

    @Autowired
    private ClientRepository repository;

    @Transactional( readOnly = true )
    public Page<ClientDTO> findAllPaged( PageRequest pageRequest ){
        Page<Client> list = repository.findAll( pageRequest );

        return list.map( ClientDTO::new );
    }

    @Transactional( readOnly = true )
    public ClientDTO findById( Long id ) {
        Optional<Client> obj = repository.findById( id );
        Client entity = obj.orElseThrow( () -> new ResourceNotFoundException( "Entity not Found" ) );
        return new ClientDTO( entity );
    }

    @Transactional
    public ClientDTO insert( ClientDTO dto ) {
        Client entity = new Client();
        copyDtoToEntity( dto, entity );
        entity = repository.save( entity );
        return new ClientDTO( entity );
    }

    private void copyDtoToEntity( ClientDTO dto, Client entity ) {
        entity.setName( dto.getName() );
        entity.setCpf( dto.getCpf() );
        entity.setIncome( dto.getIncome() );
        entity.setBirthDate( dto.getBirthDate() );
        entity.setChildren( dto.getChildren() );
    }

    @Transactional
    public ClientDTO update( Long id, ClientDTO dto ) {
        try {
            Client entity = repository.getOne( id );
            entity.setName(dto.getName());
            entity.setCpf( dto.getCpf() );
            entity.setIncome( dto.getIncome() );
            entity.setBirthDate( dto.getBirthDate() );
            entity.setChildren( dto.getChildren() );
            entity = repository.save( entity );

            return new ClientDTO( entity );
        }catch( EntityNotFoundException e ){
            throw new ResourceNotFoundException( "Id not found " + id );
        }
    }

    public void delete( Long id ) {
        try{
            repository.deleteById( id );
        }catch( EmptyResultDataAccessException e ){
            throw new ResourceNotFoundException( "Id not found " + id );
        }catch( DataIntegrityViolationException e ){
            throw new DatabaseException( "Integrity violation ");
        }
    }
}