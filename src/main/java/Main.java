import DAO.ClientDAO;
import DAO.GenericDAO;
import DAO.RentDAO;
import entities.Client;
import entities.Rent;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getById(Client.class, 1);
        RentDAO rentDAO = new RentDAO();
        int result = rentDAO.insertRent(900001, 1, 4, 6, 7)
                + rentDAO.insertRent(900002, 1, 5, 6, 7);
        System.out.println("Строк вставлено: " + result);
        System.out.println();

        Session session = RentDAO.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Rent> criteriaQuery = builder.createQuery(Rent.class);
        Root<Rent> root = criteriaQuery.from(Rent.class);
        criteriaQuery.select(root)
                .where(builder.equal(root.get("clientId").get("clientId"), client.getClientId()));
        List<Rent> listResult = session.createQuery(criteriaQuery).getResultList();
        for (Rent rent: listResult) {
            System.out.println(rent.getRentId() + ". " + rent);
        }
        session.close();
        System.out.println();

        result = rentDAO.delete(Rent.class, 900001) + rentDAO.delete(Rent.class, 900002);
        System.out.println("Строк удалено: " + result);
        System.out.println();

        result = clientDAO.updateInsertClient(200001, "имя", "фамилия","8888", "990990", "89999999980");
        System.out.println("Строк вставлено: " + result);
        System.out.println();

        result = clientDAO.updateInsertClient(200001, "имя1",null, null, null, null);
        System.out.println("Строк изменено: " + result);
        System.out.println();
        Client client1 = clientDAO.getById(Client.class, 200001);
        System.out.println(client1);

        List<Object[]> listTest= rentDAO.getRentClientSummary();
        int count = 0;
        for (Object[] obj: listTest){
            for (Object o : obj) {
                System.out.print(o + " ");
            }
            count++;
            if (count == 10){
                break;
            }
            System.out.println();
        }

    }
}
