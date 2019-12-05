package v.e.e.t.a.h.a.veeorm;

import java.lang.reflect.Field;

import v.e.e.t.a.h.a.veeorm.annotations.Column;

public class DbColumn {
    
    private String name;
    private Field javaField;
    private boolean isPrimary;

    public DbColumn(Field field) throws Exception {
        var colAnnot = AnnotationService.getAnnotationOrFail(field, Column.class);
    
        this.name = colAnnot.name().length() == 0 ? field.getName() : colAnnot.name();
        this.javaField = field;
        this.isPrimary = colAnnot.primary();
    }

    public String getName() { return this.name; }
    public Field getJavaField() { return this.javaField; }
    public boolean isPrimary() { return this.isPrimary; }
}
