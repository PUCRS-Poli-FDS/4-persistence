package br.pucrs.persistence.persistenceapp.controller;

import br.pucrs.persistence.persistenceapp.dao.ContatoDao;
import br.pucrs.persistence.persistenceapp.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    ContatoDao contatoDao;

    @PostMapping
    public ResponseEntity createContact(@RequestBody @NotNull Contato contato) {
        try {
            contatoDao.addUser(contato);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("success!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllContacts() {
        List<Contato> contatoList;
        try {
            contatoList = contatoDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(contatoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getContactsById(@PathVariable("id") int id) {
        Contato contato = Contato.builder().id(id).build();
        try {
            contato = contatoDao.getContactById(contato);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(contato, HttpStatus.OK);
    }
}
