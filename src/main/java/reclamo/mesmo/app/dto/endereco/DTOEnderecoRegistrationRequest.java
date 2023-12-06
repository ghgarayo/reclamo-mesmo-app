package reclamo.mesmo.app.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTOEnderecoRegistrationRequest(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        String numero,
        String complemento,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf){
}
