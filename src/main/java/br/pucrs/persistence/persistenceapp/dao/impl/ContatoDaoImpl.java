package br.pucrs.persistence.persistenceapp.dao.impl;

import br.pucrs.persistence.persistenceapp.dao.ContatoDao;
import br.pucrs.persistence.persistenceapp.model.Contato;
import br.pucrs.persistence.persistenceapp.service.CreateConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoDaoImpl implements ContatoDao {
    private String insert =  "INSERT INTO AGENDA(nome, telefone) VALUES(?, ?)";
    private String select = "SELECT * FROM AGENDA";
    private String selectById = "SELECT * FROM AGENDA WHERE ID = ?";

    @Autowired
    CreateConnection createConnection;

    @Override
    public void addUser(Contato contato) throws SQLException {
        try {
            PreparedStatement preparedStatement = createConnection.connection().prepareStatement(insert);
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getTelefone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                createConnection.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Contato> getAllUsers() throws  SQLException {
        List<Contato> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = createConnection.connection().prepareStatement(select);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                list.add(Contato.builder()
                        .id(rs.getInt(1))
                        .nome(rs.getString(2))
                        .telefone(rs.getString(3))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                createConnection.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public Contato getContactById(Contato contato) throws  SQLException {
        try {
            PreparedStatement preparedStatement = createConnection.connection().prepareStatement(selectById);
            preparedStatement.setInt(contato.getId(),1);
            ResultSet rs = preparedStatement.executeQuery();
            contato.setNome(rs.getString(2));
            contato.setTelefone(rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                createConnection.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contato;
    }
}
