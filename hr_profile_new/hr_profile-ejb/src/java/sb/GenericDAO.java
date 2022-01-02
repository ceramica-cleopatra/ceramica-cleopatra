/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface GenericDAO {
    public <T> T findEntityById(Class<T> entityClass, Object id);
    public <T> T findEntityByNamedQuery(String namedQueryName);
    public <T> T findEntityByNamedQuery(String namedQueryName,
			Map<String, Object> params);
    public <T> List<T> findListByNamedQuery(String namedQueryName);
    public <T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params);
    public <T> List<T> findListByNamedQuery(String namedQueryName, int start,
			int maxSize);
    public <T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params, int start, int maxSize);
    public <T> T findEntityByQuery(String QueryName);
    public <T> T findEntityByQuery(String QueryName, Map<String, Object> params);
    public <T> List<T> findListByQuery(String QueryName);
    public <T> List<T> findListByQuery(String QueryName,
			Map<String, Object> params);
    public <T> List<T> findListByQuery(String QueryName, int start, int maxSize);
    public <T> List<T> findListByQuery(String QueryName,
			Map<String, Object> params, int start, int maxSize);
    public <T> void saveEntity(T entity);
    public <T> T updateEntity(T entity);
    public <T> void deleteEntity(T entity);
    public void executeDeleteQuery(String jpqlQuery);
    public void executeDeleteQuery(String query, Map<String, Object> params);
    public double getMaxIdForEntity(String idColumn, String entityName);
    public <T> T executeQuery(String query);
    public <T> T executeQuery(String queryString, Map<String, Object> params);
    public void executeVoidQuery(String queryString, Map<String, Object> params);
    public <T> List<T> executeListQuery(String queryString,
			Map<String, Object> params);
}
