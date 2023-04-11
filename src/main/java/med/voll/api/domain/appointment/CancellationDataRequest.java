package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancellationDataRequest(
        @NotNull
        Long idAppointment,
        @NotNull
        ReasonOfCancellation reason
) {
}
