package DAO;

import entities.Client;
import entities.Rent;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;

import javax.persistence.criteria.*;
import java.util.List;

public class RentDAO extends GenericDAO<Rent>{
    public List<Object[]> getRentClientSummary() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Rent> rentRoot = criteriaQuery.from(Rent.class);
        Join<Rent, Client> clientJoin = rentRoot.join("clientId", JoinType.INNER);

        Expression<Integer> clientId = rentRoot.get("clientId").get("clientId");
        Expression<String> clientName = clientJoin.get("name");
        Expression<String> clientSurname = clientJoin.get("surname");
        Expression<Long> rentCount = builder.count(rentRoot.get("cost"));
        Expression<Float> rentSum = builder.sum(rentRoot.get("cost"));

        criteriaQuery.multiselect(clientId, clientName, clientSurname, rentCount, rentSum);
        criteriaQuery.groupBy(clientId, clientName, clientSurname);
        return session.createQuery(criteriaQuery).getResultList();
    }
    public int insertRent(int rentId, int clientId, int inventoryId, int staffId, int usingHours){
        int result = 0;
        try (Session session = getSession()) {
            begin();
            String sql = "SELECT add_rent(:rentId, :clientId, :inventoryId, :staffId, :usingHours) as result";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("rentId", rentId);
            query.setParameter("clientId", clientId);
            query.setParameter("inventoryId", inventoryId);
            query.setParameter("staffId", staffId);
            query.setParameter("usingHours", usingHours);
            query.addScalar("result", IntegerType.INSTANCE);
            result = (int) query.uniqueResult();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return result;
    }
}
