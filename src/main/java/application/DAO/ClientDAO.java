package application.DAO;

import application.entities.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO extends GenericDAO<Client> {
    public String getNameById(int id){
        Client client = getById(Client.class, id);
        return client.getName();
    }

    public int updateInsertClient(int id, String name, String surname,
                            String passportSeries, String passportNumber,
                            String phoneNumber){
        Client client = getById(Client.class, id);
        if (id > 0) {
            if (client == null) {
                client = new Client();
                client.setClientId(id);
            }
        } else {
            return 0;
        }
        if (checkString(name, 0, 50)){
            client.setName(name);
        }
        if (checkString(surname, 0, 50)){
            client.setSurname(surname);
        }
        if (checkString(passportSeries, 3, 4)
                && checkString(passportNumber, 5, 6)
                && passportSeries.matches("\\d+")
                && passportNumber.matches("\\d+")
                && isUnique(Client.class,
                "passportSeries", "passportNumber",
                passportSeries, passportNumber)){
            client.setPassportNumber(passportNumber);
            client.setPassportSeries(passportSeries);
        }
        if (checkString(phoneNumber, 10, 11)
            && phoneNumber.matches("^8[0-9]{10}$")
            && isUnique(Client.class, "phoneNumber",
                null, phoneNumber, null)){
            client.setPhoneNumber(phoneNumber);
        }
        return saveOrUpdate(client);
    }


}
