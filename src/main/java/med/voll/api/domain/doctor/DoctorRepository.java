package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select m from Doctor m
            where
            m.active = 1
            and
            m.specialty = :specialty
            and
            m.id not in(
                select c.doctor.id from Appointment c
                where
                c.data = :data
                and
                c.reason is null
            )
            order by rand()
            limit 1
            """)
    Doctor chooseFreeRandomDoctorOnDate(Specialty specialty, LocalDateTime data);

    @Query("""
            select m.active
            from Doctor m
            where
            m.id = :id
            """)
    Boolean findActiveById(Long id);
}
