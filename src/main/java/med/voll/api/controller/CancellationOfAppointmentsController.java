package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.CancellationDataRequest;
import med.voll.api.domain.appointment.ConsultationSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cancellations")
@SecurityRequirement(name = "bearer-key")
public class CancellationOfAppointmentsController {

    @Autowired
    private ConsultationSchedule consultationSchedule;

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid CancellationDataRequest data) {
        consultationSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }

}
