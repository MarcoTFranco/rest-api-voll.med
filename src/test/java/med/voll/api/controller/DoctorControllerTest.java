package med.voll.api.controller;

import med.voll.api.domain.adress.Address;
import med.voll.api.domain.adress.AddressData;
import med.voll.api.domain.doctor.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorDataRequest> doctorDataRequestJSON;

    @Autowired
    private JacksonTester<DataDetailsDoctor> dataDetailsDoctorJSON;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void registerScenario1() throws Exception {

        var response = mvc
                .perform(post("/doctors"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void registerScenario2() throws Exception {
        var doctorDataRequest = new DoctorDataRequest(
                "medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialty.cardiologia,
                dataAddress());

        Mockito.when(repository.save(Mockito.any())).thenReturn(new Doctor(doctorDataRequest));

        var response = mvc
                .perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorDataRequestJSON.write(doctorDataRequest).getJson()))
                .andReturn().getResponse();

        var dataDetailsDoctor = new DataDetailsDoctor(
                null,
                doctorDataRequest.name(),
                doctorDataRequest.email(),
                doctorDataRequest.crm(),
                doctorDataRequest.specialty(),
                new Address(doctorDataRequest.address())
        );
        var jsonExpected = dataDetailsDoctorJSON.write(dataDetailsDoctor).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
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