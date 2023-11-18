package DAO;

import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class GenericDAO<T> extends DAO {
    public T getById(Class<T> type, int id) {
        try (Session session = getSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    public int saveOrUpdate(T entity) {
        try (Session session = getSession()) {
            begin();
            session.saveOrUpdate(entity);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Class<T> type, int id) {
        try (Session session = getSession()) {
            begin();
            T entity = session.get(type, id);
            if (entity != null) {
                session.delete(entity);
            }
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return 0;
        }
    }

    public List<T> getAll(Class<T> type) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).getResultList();
    }
    public List<T> getEntitiesBetweenIds(Class<T> type, int minId, int maxId, String id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root)
                .where(builder.between(root.get(id), minId, maxId));
        return session.createQuery(criteriaQuery).getResultList();
    }

    public boolean isUnique(Class<T> type, String fieldOne,
                            String fieldTwo, String valueOne,
                            String valueTwo) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(builder.count(root));
        if (fieldTwo != null) {
            criteriaQuery.where(
                    builder.and(
                            builder.equal(root.get(fieldOne), valueOne),
                            builder.equal(root.get(fieldTwo), valueTwo)
                    )
            );
        } else {
            criteriaQuery.where(builder.equal(root.get(fieldOne), valueOne));
        }
        Long count = session.createQuery(criteriaQuery).getSingleResult();
        return count == 0;
    }
    public static boolean checkString(String string, int minLen, int maxLen){
        return (string != null) && (string.length() <= maxLen) && (string.length() > minLen);
    }
}