package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.appointment.ConsultationSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentSchedulingController {

    @Autowired
    private ConsultationSchedule consultationSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity toSchedule(@RequestBody @Valid AppointmentDataRequest data) {
        var dto = consultationSchedule.toSchedule(data);
        return ResponseEntity.ok(dto);
    }

}
