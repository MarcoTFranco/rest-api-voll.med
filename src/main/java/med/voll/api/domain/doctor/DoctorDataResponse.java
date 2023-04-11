package med.voll.api.domain.doctor;

public record DoctorDataResponse(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorDataResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
