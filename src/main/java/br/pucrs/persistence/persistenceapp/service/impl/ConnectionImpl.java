package br.pucrs.persistence.persistenceapp.service.impl;

import br.pucrs.persistence.persistenceapp.model.Contato;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionImpl {

    private String insert =  "INSERT INTO AGENDA(nome, telefone) VALUES(?, ?)";
    private String select = "SELECT * FROM AGENDA";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:~/Documents/Facul/FundamentosDeSW/git/persistenceapp/data/agenda", "sa", "password");
    }

    public void addUser(Contato contato) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection().prepareStatement(insert);
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getTelefone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getAllUsers() throws  SQLException {
        List<Contato> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection().prepareStatement(select);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                list.add(Contato.builder().nome(rs.getString(2)).telefone(rs.getString(3)).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
