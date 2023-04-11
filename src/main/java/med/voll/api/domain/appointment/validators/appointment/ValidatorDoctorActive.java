package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorActive implements ValidatorConsultationSchedule {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validate(AppointmentDataRequest data) {
        if (data.idDoctor() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(data.idDoctor());

        if (!doctorIsActive) {
            throw new ValidationException("The doctor needs to be active to be able to accept an appointment");
        }
    }

}
