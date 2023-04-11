package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentDataRequest;
import med.voll.api.domain.appointment.ConsultationSchedule;
import med.voll.api.domain.appointment.DataDetailsAppointment;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentSchedulingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDataRequest> appointmentDataRequestJson;

    @Autowired
    private JacksonTester<DataDetailsAppointment> dataDetailsAppointmentJson;

    @MockBean
    private ConsultationSchedule consultationSchedule;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void toScheduleScenario1() throws Exception {
        var response = mvc
                .perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void toScheduleScenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(2);
        var specialty = Specialty.cardiologia;

        var dataDetailsAppointment = new DataDetailsAppointment(null, 2l, 2l, data);

        Mockito.when(consultationSchedule.toSchedule(Mockito.any()))
                .thenReturn(dataDetailsAppointment);

        var response = mvc
                .perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDataRequestJson
                                .write(new AppointmentDataRequest(2l, 2l, data, specialty))
                                .getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = dataDetailsAppointmentJson
                .write(dataDetailsAppointment)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

}