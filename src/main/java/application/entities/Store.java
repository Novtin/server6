package application.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "public", catalog = "store_db")
public class Store {
    @Id
    @Column(name = "store_id", nullable = false)
    private Integer storeId;
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(storeId, store.storeId) && Objects.equals(address, store.address) && Objects.equals(phoneNumber, store.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, address, phoneNumber);
    }
}
