package med.voll.api.domain.doctor;

import med.voll.api.domain.adress.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDataRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void chooseFreeRandomDoctorOnDateScenario1() {
        var data = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = registerDoctor("medico", "medico@gmail.com", "123456", Specialty.cardiologia);
        var patient = registerPatient("paciente", "paciente@gmail.com", "000-000-000.00");
        registerConsultation(doctor, patient, data);

        var doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.cardiologia, data);

        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void chooseFreeRandomDoctorOnDateScenario2() {
        var data = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = registerDoctor("medico", "medico@gmail.com", "123456", Specialty.cardiologia);

        var doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.cardiologia, data);

        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Appointment(null, patient, doctor, data, null));
    }

    private Doctor registerDoctor(String nome, String email, String crm, Specialty specialty) {
        var medico = new Doctor(dataDoctor(nome, email, crm, specialty));
        em.persist(medico);
        return medico;
    }

    private Patient registerPatient(String nome, String email, String cpf) {
        var paciente = new Patient(dataPatient(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DoctorDataRequest dataDoctor(String nome, String email, String crm, Specialty specialty) {
        return new DoctorDataRequest(
                nome,
                email,
                "61999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private PatientDataRequest dataPatient(String nome, String email, String cpf) {
        return new PatientDataRequest(
                nome,
                email,
                "61999999999",
                cpf,
                dataAddress()
        );
    }

    private AddressData dataAddress() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "20",
                "12345678",
                "Brasilia",
                "DF",
                null
        );
    }
}