package med.voll.api.domain.adress;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    @Column(name = "property_number")
    private String propertyNumber;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String uf;
    private String complement;

    public Address(AddressData addressData) {
        this.street = addressData.street();
        this.neighborhood = addressData.neighborhood();
        this.propertyNumber = addressData.propertyNumber();
        this.zipCode = addressData.zipCode();
        this.city = addressData.city();
        this.uf = addressData.uf();
        this.complement = addressData.complement();
    }
}
