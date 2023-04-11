package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Address;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;
    private Address address;
    private Boolean active;

    public Patient(PatientDataRequest patientDataRequest) {
        this.active = true;
        this.name = patientDataRequest.name();
        this.email = patientDataRequest.email();
        this.telephone = patientDataRequest.telephone();
        this.cpf = patientDataRequest.cpf();
        this.address = new Address(patientDataRequest.address());
    }

    public void patientUpdate(PatientDataUpdate patientData) {
        this.name = patientData.name();
        this.telephone = patientData.telephone();
        this.address = new Address(patientData.address());
    }

    public void delete() {
        this.active = false;
    }
}
