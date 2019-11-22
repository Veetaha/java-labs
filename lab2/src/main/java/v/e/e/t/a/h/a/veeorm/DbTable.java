package v.e.e.t.a.h.a.veeorm;

import java.sql.ResultSet;
import java.util.Arrays;

import v.e.e.t.a.h.a.veeorm.annotations.Table;

public class DbTable<T> {
    
    private Class<T> entityClass;
    private String name;
    private DbColumns columns;

    public DbTable(Class<T> entityClass) throws Exception {
        this.entityClass = entityClass;
        this.name = AnnotationService.getAnnotationOrFail(entityClass, Table.class).name();
        this.columns = new DbColumns(() -> Arrays.stream(entityClass.getDeclaredFields()).iterator());
    }

    public Class<T> getEntityClass() { return this.entityClass; }
    public String getName() { return this.name; }
    public DbColumns getColumns() { return this.columns; }

    public T createEntityFromResultSet(ResultSet resultSet) throws Exception {
        var entity = this.entityClass.getDeclaredConstructor().newInstance();
        
        for (var col: this.columns) {
            var field = col.getJavaField();
            field.setAccessible(true);
            var val = resultSet.getObject(col.getName());
            System.out.println(val);
            field.set(entity, val);
        }

        return entity;
    }

}
