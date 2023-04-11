package med.voll.api.domain.doctor;

import med.voll.api.domain.adress.Address;

public record DataDetailsDoctor(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty,
        Address address
) {
    public DataDetailsDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(),
                doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
