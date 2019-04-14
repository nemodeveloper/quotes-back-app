package ru.nemodev.project.quotes.repository;

import java.io.Serializable;
import java.util.List;

public interface CrudRepository<T, K extends Serializable>
{
    T getById(K id);

    List<T> getList();

    T addOrUpdate(T entity);
    List<T> addOrUpdate(List<T> entityList);

}
