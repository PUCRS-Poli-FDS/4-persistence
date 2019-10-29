package br.pucrs.persistence.persistenceapp.service.impl;

import br.pucrs.persistence.persistenceapp.service.CreateConnection;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ConnectionImpl implements CreateConnection {

    @Override
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:~/Documents/Facul/FundamentosDeSW/git/persistenceapp/data/agenda", "sa", "password");
    }


}
