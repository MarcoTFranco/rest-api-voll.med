package med.voll.api.domain.patient;

import med.voll.api.domain.adress.Address;

public record DataDetailsPatient(
        Long id,
        String name,
        String email,
        String telephone,
        String cpf,
        Address address
) {
    public DataDetailsPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(),
                patient.getTelephone(), patient.getCpf(), patient.getAddress());
    }
}
