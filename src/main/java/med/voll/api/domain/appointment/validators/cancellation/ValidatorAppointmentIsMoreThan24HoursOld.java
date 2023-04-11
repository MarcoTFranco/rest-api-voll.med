package med.voll.api.domain.appointment.validators.cancellation;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.CancellationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorAppointmentIsMoreThan24HoursOld implements ValidatorConsultationCancellation {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(CancellationDataRequest data) {
        var now = LocalDateTime.now();
        var dateAppointment = appointmentRepository.findDataById(data.idAppointment());
        var differenceInDays = Duration.between(now, dateAppointment).toDays();

        if (differenceInDays < 1) {
            throw new ValidationException("Consultation must be cancel at least 1 day in advance");
        }
    }

}
