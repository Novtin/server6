package DAO;

import entities.SportsEquipment;
import entities.Staff;
import entities.Store;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends GenericDAO<Staff>{
    public List<Staff> searchByNameAndSurname(String name, String surname) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Staff> criteriaQuery = builder.createQuery(Staff.class);
        Root<Staff> root = criteriaQuery.from(Staff.class);

        List<Predicate> predicates = new ArrayList<>();

        if (checkString(name, 0, 50)) {
            predicates.add(builder.equal(root.get("name"), name));
        }

        if (checkString(surname, 0, 50)) {
            predicates.add(builder.equal(root.get("surname"), surname));
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

        return session.createQuery(criteriaQuery).getResultList();
    }
    public int updateInsertStaff(int id, String name,
                                 String surname, String position,
                                 BigDecimal salary, String phoneNumber){
        Staff staff = getById(Staff.class, id);
        if (id > 0) {
            if (staff == null) {
                staff = new Staff();
                staff.setStaffId(id);
            }
        } else {
            return 0;
        }
        if (checkString(name, 0, 50)){
            staff.setName(name);
        }
        if (checkString(surname, 0, 50)){
            staff.setSurname(surname);
        }
        if (checkString(position, 0, 50)){
            staff.setPosition(position);
        }
        if (salary != null){
            staff.setSalary(salary);
        }
        if (checkString(phoneNumber, 10, 11)
                && phoneNumber.matches("^8[0-9]{10}$")
                && isUnique(Staff.class, "phoneNumber",
                null, phoneNumber, null)){
            staff.setPhoneNumber(phoneNumber);
        }
        try {
            saveOrUpdate(staff);
        } catch (Exception e){
            return 0;
        }
        return 1;
    }
}
