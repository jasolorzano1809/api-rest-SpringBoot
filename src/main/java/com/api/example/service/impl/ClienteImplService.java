package com.api.example.service.impl;

import com.api.example.model.dao.ClienteDao;
import com.api.example.model.dto.ClienteDto;
import com.api.example.model.entity.Cliente;
import com.api.example.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return (List) clienteDao.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .id_cliente(clienteDto.getId_cliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fech_registro(clienteDto.getFech_registro())
                .build();
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);

    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);
    }
}
