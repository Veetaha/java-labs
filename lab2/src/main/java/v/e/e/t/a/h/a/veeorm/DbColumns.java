package v.e.e.t.a.h.a.veeorm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represens java to sql columns and sql columns to java fields map collection.
 * 
 * Currently supports only one primary collumn.
 */
public class DbColumns implements Iterable<DbColumn> {
    private DbColumn primaryColumn;
    private HashMap<String, DbColumn> javaNameMap;
    private HashMap<String, DbColumn> colNameMap;

    public DbColumns(Iterable<Field> fields) throws Exception {
        this.javaNameMap = new HashMap<>();
        this.colNameMap = new HashMap<>();

        for (var field : fields) {
            var col = new DbColumn(field);
            trySetPrimaryColumn(col);

            if (this.colNameMap.put(col.getName(), col) != null)
                throw new Exception("Duplicate column name within one table: " + col);

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

    public DbColumn getColumnBySqlName(String javaName) {
        return this.javaNameMap.get(javaName);
    }

    @Override
    public Iterator<DbColumn> iterator() {
        return this.javaNameMap.values().iterator();
    }
}
