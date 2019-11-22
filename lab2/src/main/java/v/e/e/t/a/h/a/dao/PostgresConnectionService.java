package v.e.e.t.a.h.a.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;
import io.github.cdimascio.dotenv.Dotenv;

class PostgresConnectionService {
    
    public static Connection createConnection() throws SQLException {
        var dotenv = Dotenv.load();
        var dataSource = new PGSimpleDataSource();
        dataSource.setServerName(dotenv.get("DB_HOST"));
        dataSource.setDatabaseName(dotenv.get("DB_NAME"));
        dataSource.setPortNumber(Integer.parseInt(dotenv.get("DB_PORT")));
        dataSource.setUser(dotenv.get("DB_USERNAME"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));
        return dataSource.getConnection();
    }
}
