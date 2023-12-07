package reclamo.mesmo.app.dto.pessoajuridica;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;

public record DTOPessoaJuridicaRegistration(
        @NotBlank
        String razaoSocial,
        @NotBlank
        @Pattern(regexp = "\\d{14}")
        String cnpj,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String telefone,
        @Valid
        DTOEndereco endereco
) {
}
