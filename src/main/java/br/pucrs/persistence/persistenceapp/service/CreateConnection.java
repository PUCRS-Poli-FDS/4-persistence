package br.pucrs.persistence.persistenceapp.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface CreateConnection {
    Connection connection() throws SQLException;
}
