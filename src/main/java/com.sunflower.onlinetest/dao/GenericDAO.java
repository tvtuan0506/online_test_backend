package com.sunflower.onlinetest.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;

public class GenericDAO<T extends iEntity> {

    private Class<T> persistenceClass;

    @PersistenceContext(unitName = "online_test_persistence")
    private EntityManager entityManager;

    private Class<T> getPersistenceClass() {
        if (this.persistenceClass == null) {
            this.persistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }
        return this.persistenceClass;
    }

    public T findById(Integer id) {
        return this.entityManager.find(getPersistenceClass(), id);
    }

    public void save(T entity) {
        this.entityManager.persist(entity);
    }

    public T update(T entity) {
        return this.entityManager.merge(entity);
    }

    public void remove(Integer id) {
        T entity = findById(id);
        if (entity != null) {
            this.entityManager.remove(entity);
        }
    }

    public void removeEntity(T entity) {
        this.entityManager.remove(entityManager.contains(entity) ? entity : update(entity));
    }

    public TypedQuery<T> createTypeQuery(String query) {
        return this.entityManager.createQuery(query, getPersistenceClass());
    }
}
