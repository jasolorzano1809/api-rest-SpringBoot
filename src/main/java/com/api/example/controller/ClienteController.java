package com.api.example.controller;

import com.api.example.model.dto.ClienteDto;
import com.api.example.model.entity.Cliente;
import com.api.example.model.payload.MensajeResponse;
import com.api.example.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;


    @GetMapping("clientes")
    public ResponseEntity<?> listAll(){
        List<Cliente> allClientes= clienteService.findAll();

        if(allClientes.isEmpty() || allClientes == null){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existen registros en la base de datos")
                            .object(null)
                            .build(),
                    HttpStatus.OK);
        }
        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(allClientes)
                        .build(),
                HttpStatus.OK);
    }


    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        Cliente clienteSave = null;
        try {
             clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Guardado Correctamente")
                            .object(ClienteDto.builder()
                                    .id_cliente(clienteSave.getId_cliente())
                                    .nombre(clienteSave.getNombre())
                                    .apellido(clienteSave.getApellido())
                                    .correo(clienteSave.getCorreo())
                                    .fech_registro(clienteSave.getFech_registro())
                                    .build())
                            .build()
                    ,HttpStatus.CREATED
            );

        }catch (DataAccessException exDt){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }



    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto,@PathVariable Integer id){
        Cliente clienteUpdate = null;
        try {
            if(clienteService.existsById(id)){
                clienteDto.setId_cliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Actualizado Correctamente")
                                .object(ClienteDto.builder()
                                        .id_cliente(clienteUpdate.getId_cliente())
                                        .nombre(clienteUpdate.getNombre())
                                        .apellido(clienteUpdate.getApellido())
                                        .correo(clienteUpdate.getCorreo())
                                        .fech_registro(clienteUpdate.getFech_registro())
                                        .build())
                                .build()
                        ,HttpStatus.CREATED
                );
            }else{
                return  new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El cliente que intenta actualizar no existe en la base de datos")
                                .object(null)
                                .build(),
                        HttpStatus.METHOD_NOT_ALLOWED);
            }


        }catch (DataAccessException exDt){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje(exDt.getMessage())
                        .object(null)
                        .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        if(cliente == null){
            return  new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro no existe")
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
         return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .id_cliente(cliente.getId_cliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .correo(cliente.getCorreo())
                                .fech_registro(cliente.getFech_registro())
                                .build())
                        .build(),
                HttpStatus.OK);
    }
}
