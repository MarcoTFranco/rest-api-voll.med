package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.AddressData;

public record DoctorDataUpdate(
        @NotBlank
        String name,
        @NotBlank
        String telephone,
        @NotNull
        @Valid
        AddressData address
) {
}
