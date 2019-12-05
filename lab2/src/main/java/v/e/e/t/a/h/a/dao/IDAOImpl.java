package v.e.e.t.a.h.a.dao;

import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(long id);
    List<T> getEntityList();
}
