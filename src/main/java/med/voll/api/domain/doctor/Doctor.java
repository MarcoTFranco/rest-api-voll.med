package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Address;

@Entity(name = "Doctor")
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    @Enumerated
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorDataRequest doctorDataRequest) {
        this.active = true;
        this.name = doctorDataRequest.name();
        this.email = doctorDataRequest.email();
        this.telephone = doctorDataRequest.telephone();
        this.crm = doctorDataRequest.crm();
        this.specialty = doctorDataRequest.specialty();
        this.address = new Address(doctorDataRequest.address());
    }

    public void doctorUpdate(DoctorDataUpdate doctorData) {
        this.name = doctorData.name();
        this.telephone = doctorData.telephone();
        this.address = new Address(doctorData.address());
    }

    public void delete() {
        this.active = false;
    }
}
