package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAnotherAppointmentAtTheSameTime implements ValidatorConsultationSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentDataRequest data) {
        var doctorHasAnotherConsultationAtTheSameTime = appointmentRepository.existsByDoctorIdAndData(data.idDoctor(), data.date());

        if (doctorHasAnotherConsultationAtTheSameTime) {
            throw new ValidationException("Doctor already has another appointment scheduled at the same time");
        }

    }

}
