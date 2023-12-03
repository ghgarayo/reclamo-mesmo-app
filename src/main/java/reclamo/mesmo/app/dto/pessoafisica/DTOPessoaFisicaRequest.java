package reclamo.mesmo.app.dto.pessoafisica;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import reclamo.mesmo.app.dto.endereco.DTOEnderecoRegisterRequest;

public record DTOPessoaFisicaRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String telefone,
        @Valid
        DTOEnderecoRegisterRequest endereco
) {

}
