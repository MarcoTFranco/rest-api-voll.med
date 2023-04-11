package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DataDetailsAppointment(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime data
) {
    public DataDetailsAppointment(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(),
                appointment.getPatient().getId(), appointment.getData());
    }
}
