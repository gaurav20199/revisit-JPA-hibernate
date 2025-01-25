package entities;

import jakarta.persistence.*;

@Entity
public class PersonBidirectional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinColumn(name="address") // by default name will be address(field name)_addressId(pk of the reference field)
    private AddressBidirectional address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public AddressBidirectional getAddress() {
        return address;
    }

    public void setAddress(AddressBidirectional address) {
        this.address = address;
    }
}
