package v.e.e.t.a.h.a.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import v.e.e.t.a.h.a.veeorm.DbTable;

public class DAOImpl<T> implements IDAOImpl<T> {

    DbTable<T> dbTable;

    Connection dbConnection;
    

    public DAOImpl(Class<T> entityClass) throws Exception {
        this(entityClass, PostgresConnectionService.createConnection());
    }

    public DAOImpl(Class<T> entityClass, Connection dbConnection) throws Exception {
        this.dbTable = new DbTable<>(entityClass);
        this.dbConnection = dbConnection;
    }


    @Override
    public T getEntity(long id) {
        try {
            var resultSet = dbConnection
                .createStatement()
                .executeQuery(createSelectByIdQuery(id));

            if (!resultSet.next()) return null;
            
            return this.dbTable.createEntityFromResultSet(resultSet);
        } catch (Exception e) {
            System.exit(228);
            return null;
        }
    }

    @Override
    public List<T> getEntityList() {
        try {
            var resultSet = dbConnection
                .createStatement()
                .executeQuery(createSelectAllQuery());

            var result = new ArrayList<T>();
            while (resultSet.next()) {
                result.add(this.dbTable.createEntityFromResultSet(resultSet));
            }
            return result;
        } catch (Exception e) {
            System.exit(228);
            return null;
        }
    }

    private String createSelectByIdQuery(long id) {
        return new StringBuilder("select * from \"")
            .append(this.dbTable.getName())
            .append("\" where \"")
            .append(this.dbTable.getColumns().getPrimaryColumn().getName())
            .append("\" = ")
            .append(id)
            .toString();
    }

    private String createSelectAllQuery() {
        return new StringBuilder("select * from \"")
            .append(this.dbTable.getName())
            .append('"')
            .toString();
    }
}
