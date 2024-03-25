package com.api.example.service;

import com.api.example.model.dto.ClienteDto;
import com.api.example.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}
