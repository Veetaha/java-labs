package v.e.e.t.a.h.a.veeorm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

import v.e.e.t.a.h.a.veeorm.annotations.MappedSuperclass;

/**
 * Represens java to sql columns and sql columns to java fields map collection.
 * 
 * Currently supports only one primary collumn.
 */
public class DbColumns implements Iterable<DbColumn> {
    private DbColumn primaryColumn;
    private HashMap<String, DbColumn> javaNameMap;

    public DbColumns(Class<?> entityClass) throws Exception {
        this.javaNameMap = new HashMap<>();

        addFields(entityClass.getDeclaredFields());
        var superClass = entityClass.getSuperclass();
        while (superClass != Object.class && superClass != null) { // naverochku
            AnnotationService.getAnnotationOrFail(superClass, MappedSuperclass.class);
            addFields(superClass.getDeclaredFields());
            superClass = superClass.getSuperclass();
        }
        if (primaryColumn == null) {
            throw new Exception("Table without a primary column is not permitted");
        }

    }

    private void addFields(Field[] fields) throws Exception {
        for (var field : fields) {
            var col = new DbColumn(field);
            trySetPrimaryColumn(col);

            this.javaNameMap.put(col.getJavaField().getName(), col); // field names are always unique
        }
    }

    private void trySetPrimaryColumn(DbColumn suspect) throws Exception {
        if (!suspect.isPrimary()) return;

        if (this.primaryColumn == null) {
            this.primaryColumn = suspect;
            return;
        }

        throw new Exception("Multiple primary columns are not supported yet!");
    }

    public DbColumn getPrimaryColumn() {
        return this.primaryColumn;
    }

    public DbColumn getColumnByJavaName(String javaName) {
        return this.javaNameMap.get(javaName);
    }

    @Override
    public Iterator<DbColumn> iterator() {
        return this.javaNameMap.values().iterator();
    }
}
