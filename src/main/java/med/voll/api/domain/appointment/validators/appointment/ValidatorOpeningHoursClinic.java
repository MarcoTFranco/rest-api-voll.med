package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorOpeningHoursClinic implements ValidatorConsultationSchedule {

    @Override
    public void validate(AppointmentDataRequest data) {
        var consultationDate = data.date();

        var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = consultationDate.getHour() < 7;
        var afterTheClinicCloses = consultationDate.getHour() > 18;

        if (sunday || beforeTheClinicOpens || afterTheClinicCloses) {
            throw new ValidationException("Consultation outside clinic opening hours");
        }

    }

}
