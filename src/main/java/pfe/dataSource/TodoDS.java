package pfe.dataSource;

import pfe.annotations.DataSource;
import pfe.models.Todo;

import java.util.ArrayList;

@DataSource(id = "dtSrcTodos")
public class TodoDS implements  Persistance<Todo>{
    private static ArrayList<Todo> todos = new ArrayList<>();
    @Override
    public ArrayList<Todo> getAll() {
        return todos;
    }

    @Override
    public Todo getById(Object id) {
        int typedId = (int) id;

        for (Todo todo: todos) {
            if (typedId == todo.getId()) return todo;
        }

        return null;
    }


    @Override
    public Todo delete(Object id) {
        int typedId = (int) id;

        for (Todo todo: todos) {
            if (typedId == todo.getId()) {
                todos.remove(todo);

                return todo;
            }
        }

        return null;
    }

    @Override
    public Todo save(Todo obj) {
        for (Todo todo: todos) {
            if (todo.getId() == obj.getId()) {
                return todo = obj;
            }
        }

        todos.add(obj);

        return obj;
    }
}
