package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public record AppointmentDataRequest(
        @NotNull
        Long idPatient,
        @Nullable
        Long idDoctor,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty
) {
}
