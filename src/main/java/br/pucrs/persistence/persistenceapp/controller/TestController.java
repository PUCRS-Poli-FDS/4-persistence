package br.pucrs.persistence.persistenceapp.controller;

import br.pucrs.persistence.persistenceapp.model.Contato;
import br.pucrs.persistence.persistenceapp.service.impl.ConnectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    ConnectionImpl connection;

    @GetMapping
    public ResponseEntity getTest() {
        try {
            connection.addUser(Contato.builder().nome("Mario Specht").telefone("51 986596937").build());
            return new ResponseEntity(connection.getAllUsers(), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
