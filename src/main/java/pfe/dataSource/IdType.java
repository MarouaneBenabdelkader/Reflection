package pfe.dataSource;

import pfe.annotations.Id;

import java.lang.reflect.Field;

public class IdType {
    public static Class getIdType(Class clazz) throws Exception {
        Field [] fields = clazz.getFields();
        if(fields.length == 0) throw new Exception("Can't get id from class with no fields");
        // by convention
        Class idType = fields[0].getType();
        for (Field field:fields) {
            if(field.isAnnotationPresent(Id.class)) return field.getType();
        }

        return idType;
    }
}
