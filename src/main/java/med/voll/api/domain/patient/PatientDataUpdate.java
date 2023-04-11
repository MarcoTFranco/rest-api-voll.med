package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.adress.AddressData;
import org.springframework.lang.NonNull;

public record PatientDataUpdate(
        @NotBlank
        String name,
        @NotBlank
        String telephone,
        @NonNull
        @Valid
        AddressData address
) {
}
