package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.validators.appointment.ValidatorConsultationSchedule;
import med.voll.api.domain.appointment.validators.cancellation.ValidatorConsultationCancellation;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private List<ValidatorConsultationSchedule> validators;
    @Autowired
    private List<ValidatorConsultationCancellation> validatorsCancellation;

    public DataDetailsAppointment toSchedule(AppointmentDataRequest data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("The informed patient ID does not exist!");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("The informed doctor ID does not exist!");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = choiceDoctor(data);

        if (doctor == null) {
            throw new ValidationException("There is no doctor available on that date!");
        }

        var appointment = new Appointment(null, patient, doctor, data.date(), null);

        appointmentRepository.save(appointment);
        return new DataDetailsAppointment(appointment);
    }

    public void cancel(CancellationDataRequest data) {

        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("The informed appointment ID does not exist!");
        }

        validatorsCancellation.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }

    private Doctor choiceDoctor(AppointmentDataRequest data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when the doctor is not chosen!");
        }

        return doctorRepository.chooseFreeRandomDoctorOnDate(data.specialty(), data.date());
    }
}
