package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorHoursAdvance implements ValidatorConsultationSchedule {

    @Override
    public void validate(AppointmentDataRequest data) {
        var differenceInMinutes = Duration.between(LocalDateTime.now(), data.date()).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Consultation must be scheduled at least 30 minutes in advance");
        }
    }

}
