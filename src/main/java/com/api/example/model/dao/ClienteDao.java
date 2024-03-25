package com.api.example.model.dao;


import com.api.example.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente,Integer> {

}
