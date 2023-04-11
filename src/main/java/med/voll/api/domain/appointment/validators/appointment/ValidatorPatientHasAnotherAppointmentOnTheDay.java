package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientHasAnotherAppointmentOnTheDay implements ValidatorConsultationSchedule {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentDataRequest data) {
        var patientHasAnotherAppointmentOnTheDay = appointmentRepository.existsByPatientIdAndDataBetween(
                data.idPatient(), data.date().withHour(7), data.date().withHour(18)
        );
        if (patientHasAnotherAppointmentOnTheDay) {
            throw new ValidationException("The patient already has an appointment scheduled for that day");
        }
    }

}
