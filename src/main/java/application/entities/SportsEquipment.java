package application.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sports_equipment", schema = "public", catalog = "store_db")
public class SportsEquipment {
    @Id
    @Column(name = "equipment_id", nullable = false)
    private Integer equipmentId;
    @Basic
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "cost", nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;
    @Basic
    @Column(name = "penalty", nullable = false, precision = 12, scale = 2)
    private BigDecimal penalty;
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store storeId;

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public Integer getStoreId() {
        return storeId.getStoreId();
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsEquipment that = (SportsEquipment) o;
        return Objects.equals(equipmentId, that.equipmentId) && Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(penalty, that.penalty) && Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId, type, name, cost, penalty, storeId);
    }
}
