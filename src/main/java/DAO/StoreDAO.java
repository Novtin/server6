package DAO;

import entities.Client;
import entities.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class StoreDAO extends GenericDAO<Store>{

    public int updateInsertStore(int id, String address,
                                  String phoneNumber){
        Store store = getById(Store.class, id);
        if (id > 0) {
            if (store == null) {
                store = new Store();
                store.setStoreId(id);
            }
        } else {
            return 0;
        }
        if (checkString(address, 0, 50)){
            store.setAddress(address);
        }
        if (checkString(phoneNumber, 10, 11)
                && phoneNumber.matches("^8[0-9]{10}$")
                && isUnique(Store.class, "phoneNumber",
                null, phoneNumber, null)){
            store.setPhoneNumber(phoneNumber);
        }
        try {
            saveOrUpdate(store);
        } catch (Exception e){
            return 0;
        }
        return 1;
    }

}
