package med.voll.api.domain.patient;

public record PatientDataResponse(
        String name,
        String email,
        String cpf
) {
    public PatientDataResponse(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
