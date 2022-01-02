/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class GenericDAOImp implements GenericDAO {

    @PersistenceContext(unitName = "hr_profile-ejbPU")
    private EntityManager em;

    @Override
    public <T> T findEntityById(Class<T> entityClass, Object id) {
        System.out.println("id"+id);
            return em.find(entityClass, id);
    }

    @Override
    public <T> T findEntityByNamedQuery(String namedQueryName) {
//		return findEntityByNamedQuery(namedQueryName, null);
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T findEntityByNamedQuery(String namedQueryName,
            Map<String, Object> params) {
        Query query = em.createNamedQuery(namedQueryName);

        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            return (T) query.getResultList().get(0);
        } else {
            return null;
        }

    }

    @Override
    public <T> List<T> findListByNamedQuery(String namedQueryName) {
        return findListByNamedQuery(namedQueryName, null, -1, -1);
    }

    @Override
    public <T> List<T> findListByNamedQuery(String namedQueryName,
            Map<String, Object> params) {
        return findListByNamedQuery(namedQueryName, params, -1, -1);
    }

    @Override
    public <T> List<T> findListByNamedQuery(String namedQueryName, int start,
            int maxSize) {
        return findListByNamedQuery(namedQueryName, null, start, maxSize);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findListByNamedQuery(String namedQueryName,
            Map<String, Object> params, int start, int maxSize) {
        Query query = em.createNamedQuery(namedQueryName);
        if (start > -1) {
            query.setFirstResult(start);
        }
        if (maxSize > -1) {
            query.setMaxResults(maxSize);
        }

        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return query.getResultList();
    }

    @Override
    public <T> T findEntityByQuery(String QueryName) {
//		return findEntityByQuery(QueryName, null);
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T findEntityByQuery(String QueryName, Map<String, Object> params) {
        Query query = em.createQuery(QueryName);

        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        if (query.getResultList() != null && !query.getResultList().isEmpty()) {
            try {
                return (T) query.getResultList().get(0);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public <T> List<T> findListByQuery(String QueryName) {
        return findListByQuery(QueryName, null, -1, -1);
    }

    @Override
    public <T> List<T> findListByQuery(String QueryName,
            Map<String, Object> params) {
        return findListByQuery(QueryName, params, -1, -1);
    }

    @Override
    public <T> List<T> findListByQuery(String QueryName, int start, int maxSize) {
        return findListByQuery(QueryName, null, start, maxSize);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findListByQuery(String QueryName,
            Map<String, Object> params, int start, int maxSize) {
        Query query = em.createQuery(QueryName);
        if (start > -1) {
            query.setFirstResult(start);
        }
        if (maxSize > -1) {
            query.setMaxResults(maxSize);
        }

        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return query.getResultList();
    }

    @Override
    public <T> void saveEntity(T entity) {
        em.persist(entity);
        em.merge(entity);

    }

    @Override
    public <T> T updateEntity(T entity) {
        T e = em.merge(entity);
        return e;
    }

    @Override
    public <T> void deleteEntity(T entity) {
        em.remove(entity);
    }

    @Override
    public void executeDeleteQuery(String jpqlQuery) {
        Query query = em.createQuery(jpqlQuery);
        query.executeUpdate();
    }

    public void executeDeleteQuery(String query, Map<String, Object> params) {
        Query q = em.createQuery(query);
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }

        q.executeUpdate();
    }

    public double getMaxIdForEntity(String idColumn, String entityName) {
        double maxId = 0;
        String jpqlQuery = "SELECT MAX(" + idColumn + ") from " + entityName
                + "";
        Query query = em.createQuery(jpqlQuery);
        @SuppressWarnings("unchecked")
        List<Double> result = (List<Double>) query.getResultList();
        if (result != null && result.size() > 0) {
            maxId = result.get(0);
        }
        return maxId;

    }

    @SuppressWarnings("unchecked")
    public <T> T executeQuery(String query) {
        Query jpqlQuery = em.createQuery(query);
        return (T) jpqlQuery.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public <T> T executeQuery(String queryString, Map<String, Object> params) {
        Query query = em.createQuery(queryString);
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return (T) query.getSingleResult();
    }

    public void executeVoidQuery(String queryString, Map<String, Object> params) {
        Query query = em.createQuery(queryString);
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeListQuery(String queryString,
            Map<String, Object> params) {
        Query query = em.createQuery(queryString);
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query.getResultList();
    }
}
