package application.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "public", catalog = "store_db", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"passport_series", "phone_number"}),
                @UniqueConstraint(columnNames = {"phone_number"})})
public class Client {
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer clientId;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;
    @Basic
    @Column(name = "passport_series", nullable = false, length = 4)
    private String passportSeries;
    @Basic
    @Column(name = "passport_number", nullable = false, length = 6)
    private String passportNumber;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(passportSeries, client.passportSeries) && Objects.equals(passportNumber, client.passportNumber) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, surname, passportSeries, passportNumber, phoneNumber);
    }
}
