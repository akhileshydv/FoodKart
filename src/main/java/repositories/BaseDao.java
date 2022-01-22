package repositories;

import java.util.List;

public interface BaseDao<V, T> {
    T create(T entity);
    T find(V type);
    T update(T entity);
    List<T> findALl();
}
