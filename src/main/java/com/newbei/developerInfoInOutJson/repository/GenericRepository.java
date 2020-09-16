package com.newbei.developerInfoInOutJson.repository;

import java.util.List;
/**
 * Generic Interface with methods of model's Implementation's
 * @param <T> - model
 * @param <ID> - id: link with all model's - just like database relational link
 */
public interface GenericRepository<T,ID> {
    List<T> getAll();
    T getById(ID id);
    T save(T t);
    T update(T t);
    T deleteById(ID id);
}