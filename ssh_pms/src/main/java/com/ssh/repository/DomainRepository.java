package com.ssh.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XRog
 * On 2/2/2017.2:28 PM
 */
public interface DomainRepository<T,PK extends Serializable>{
    T load(PK id);

    T get(PK id);

    List<T> findAll();

    void persist(T entity);

    PK save(T entity);

    void saveOrUpdate(T entity);

    void delete(PK id);

    void flush();
}