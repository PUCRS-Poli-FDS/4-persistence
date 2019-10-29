package br.pucrs.persistence.persistenceapp.dao;

import br.pucrs.persistence.persistenceapp.model.Contato;

import java.sql.SQLException;
import java.util.List;

public interface ContatoDao {
    void addUser(Contato contato) throws SQLException;
    List<Contato> getAllUsers() throws  SQLException;
    Contato getContactById(Contato contato) throws  SQLException;
}
