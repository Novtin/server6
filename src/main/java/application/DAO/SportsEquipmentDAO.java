package application.DAO;

import application.entities.SportsEquipment;
import application.entities.Store;

import java.math.BigDecimal;

public class SportsEquipmentDAO extends GenericDAO<SportsEquipment>{
    public int updateInsertSportsEquipment(int id, String type,
                                 String name, BigDecimal cost, BigDecimal penalty, int store_id){
        SportsEquipment sportsEquipment = getById(SportsEquipment.class, id);
        if (id > 0) {
            if (sportsEquipment == null) {
                sportsEquipment = new SportsEquipment();
                sportsEquipment.setEquipmentId(id);
            }
        } else {
            return 0;
        }
        if (checkString(type, 0, 50)){
            sportsEquipment.setType(type);
        }
        if (checkString(name, 0, 50)){
            sportsEquipment.setName(name);
        }
        if (cost != null){
            sportsEquipment.setCost(cost);
        }
        if (penalty != null){
            sportsEquipment.setPenalty(penalty);
        }
        if (store_id > 0){
        Store store = new StoreDAO().getById(Store.class, store_id);
            if (store != null) {
                sportsEquipment.setStoreId(store);
            }
        } else {
            return 0;
        }
        try {
            saveOrUpdate(sportsEquipment);
        } catch (Exception e){
            return 0;
        }
        return 1;
    }
}
