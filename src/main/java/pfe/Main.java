package pfe;

import com.google.gson.Gson;
import org.reflections.Reflections;
import pfe.annotations.DataSource;
import pfe.annotations.Rest;
import pfe.dataSource.Persistance;
import pfe.dataSource.TodoDS;
import pfe.models.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class Main {
    public static void main(String[] args) throws Exception {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setContent("hjdsjkjsdkiljsl");
        TodoDS todoDS = new TodoDS();
        todoDS.save(todo);

        Reflections reflections = new Reflections("pfe");
        Set<Class<?>> restClasses = reflections.get(SubTypes.of(TypesAnnotated.with(Rest.class)).asClass());
        Set<Class<?>> dsrClasses = reflections.get(SubTypes.of(TypesAnnotated.with(DataSource.class)).asClass());

        Map<String, Class<?>> dtSrcMap = new HashMap<>();

        for (Class<?> clazz: dsrClasses) {
            dtSrcMap.put(clazz.getAnnotation(DataSource.class).id(), clazz);
        }

        for (Class<?> clazz: restClasses) {

            Rest annotation = clazz.getAnnotation(Rest.class);
            Class<?> dataSrcClass = dtSrcMap.get(annotation.dataSource());


            if (dataSrcClass == null) {
                throw new Exception("Does not have data source class");
            } else {

                Class <?> [] interfaces = dataSrcClass.getInterfaces();
                for (Class<?> infterClass:interfaces) {
                    if(infterClass == Persistance.class){
                        Persistance<Object> instance = (Persistance<Object>) dataSrcClass.newInstance();
                        Gson gson = new Gson();
                        ArrayList<Object> objects = instance.getAll();
                        //Object objects = instance.getById(1);
                        for (Object ob: objects) {
                            System.out.println(gson.toJson(ob));
                        }

                    }
                }
            }
        }
    }
}
