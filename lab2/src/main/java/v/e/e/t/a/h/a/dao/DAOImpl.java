package v.e.e.t.a.h.a.dao;

import java.sql.Connection;
import java.util.List;

public class DAOImpl<T> implements IDAOImpl<T> {
    Class<T> entityClass;
    Connection dbConnection;

    public DAOImpl(Class<T> entityClass) throws Exception {
        this.entityClass = entityClass;
    }

    public DAOImpl(Class<T> entityClass, Connection dbConnection) {
        this.entityClass = entityClass;
        this.dbConnection = dbConnection;
    }

    @Override
    public T getEntity(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> getEntityList() {
        throw new UnsupportedOperationException();
    }
}
