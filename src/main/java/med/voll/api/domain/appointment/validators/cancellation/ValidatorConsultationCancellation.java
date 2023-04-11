package med.voll.api.domain.appointment.validators.cancellation;

import med.voll.api.domain.appointment.CancellationDataRequest;

public interface ValidatorConsultationCancellation {

    void validate(CancellationDataRequest data);
}
