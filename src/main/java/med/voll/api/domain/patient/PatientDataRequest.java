package med.voll.api.domain.patient;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.AddressData;
import org.hibernate.validator.constraints.br.CPF;

public record PatientDataRequest(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @CPF
        @NotBlank
        String cpf,
        @NotNull
        @Valid
        AddressData address
) {
}
