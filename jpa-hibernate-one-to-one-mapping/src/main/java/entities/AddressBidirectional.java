package entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;

@Entity
public class AddressBidirectional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    private String city;

    private String pincode;

    @OneToOne(mappedBy = "address")
    private PersonBidirectional person;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public PersonBidirectional getPerson() {
        return person;
    }

    public void setPerson(PersonBidirectional person) {
        this.person = person;
    }
}
