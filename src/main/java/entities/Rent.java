package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Rent {
    @Id
    @Column(name = "rent_id", nullable = false)
    private Integer rentId;
    @Basic
    @Column(name = "start_rent", nullable = false)
    private Timestamp startRent;
    @Basic
    @Column(name = "end_rent", nullable = false)
    private Timestamp endRent;
    @Basic
    @Column(name = "cost", nullable = false, precision = 2)
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;
    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private SportsEquipment equipmentId;
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staffId;

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Timestamp getStartRent() {
        return startRent;
    }

    public void setStartRent(Timestamp startRent) {
        this.startRent = startRent;
    }

    public Timestamp getEndRent() {
        return endRent;
    }

    public void setEndRent(Timestamp endRent) {
        this.endRent = endRent;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getClientId() {
        return clientId.getClientId();
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Integer getEquipmentId() {
        return equipmentId.getEquipmentId();
    }

    public void setEquipmentId(SportsEquipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getStaffId() {
        return staffId.getStaffId();
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(rentId, rent.rentId) && Objects.equals(startRent, rent.startRent) && Objects.equals(endRent, rent.endRent) && Objects.equals(cost, rent.cost) && Objects.equals(clientId, rent.clientId) && Objects.equals(equipmentId, rent.equipmentId) && Objects.equals(staffId, rent.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentId, startRent, endRent, cost, clientId, equipmentId, staffId);
    }
}
