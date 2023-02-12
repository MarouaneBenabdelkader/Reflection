package pfe.dataSource;


import java.util.ArrayList;

public interface Persistance<T> {
    public ArrayList<T> getAll();
    public T getById(Object id);
    public T delete(Object id);
    public T save(T obj);

}
