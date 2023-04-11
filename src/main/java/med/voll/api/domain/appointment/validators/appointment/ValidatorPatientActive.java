package med.voll.api.domain.appointment.validators.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientActive implements ValidatorConsultationSchedule {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void validate(AppointmentDataRequest data) {
        var patientIsActive = patientRepository.findActiveById(data.idPatient());

        if (!patientIsActive) {
            throw new ValidationException("The patient needs to be active to be able to schedule an appointment");
        }
    }

}
