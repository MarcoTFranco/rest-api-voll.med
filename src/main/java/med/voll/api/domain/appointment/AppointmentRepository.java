package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);

    boolean existsByDoctorIdAndData(Long idDoctor, LocalDateTime date);

    @Query("""
            select m.data
            from Appointment m
            where
            m.id = :id
            """)
    LocalDateTime findDataById(Long id);
}
