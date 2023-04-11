package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataAuthenticationLogin(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
