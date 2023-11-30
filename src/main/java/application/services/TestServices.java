package application.services;

//import application.DAO.DAO;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class TestServices extends DAO {
//    public String  getName(int id){
//        String query = "SELECT name FROM client WHERE client_id = :id";
//        Query q = getSession().createSQLQuery(query).setParameter ("id", id);
//        return (String) q.list().get(0);
//    }
//
//    public List getNameBetween(int startId, int endId){
//        String query = "SELECT name FROM client WHERE client_id BETWEEN :start_id AND :end_id";
//        Query q = getSession().createSQLQuery(query)
//                .setParameter("start_id", startId)
//                .setParameter("end_id", endId);
//        return q.list();
//    }
//
//    public void insertStore(int storeId, String address, String phoneNumber) {
//        try {
//            begin(); // Начинаем транзакцию
//            String query = "INSERT INTO store (store_id, address, phone_number)" +
//                    " VALUES (:store_id, :address, :phone_number)";
//            Query q = getSession().createSQLQuery(query)
//                    .setParameter("store_id", storeId)
//                    .setParameter("address", address)
//                    .setParameter("phone_number", phoneNumber);
//            int rowsAffected = q.executeUpdate();
//            System.out.println("Строк вставлено: " + rowsAffected);
//            commit(); // Фиксируем изменения в базе данных
//        } catch (Exception e) {
//            rollback();
//            e.printStackTrace();
//        }
//    }
//}
//