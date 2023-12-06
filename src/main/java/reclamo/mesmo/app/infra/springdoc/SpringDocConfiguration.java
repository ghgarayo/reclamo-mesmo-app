package reclamo.mesmo.app.infra.springdoc;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Reclamo Mesmo API")
                        .description("""
                                API Rest da aplicação Reclamo Mesmo, desenvolvida como parte do treinamento do Go Dev,
                                da Senior Sistemas.
                                Esta API contém as funcionalidades de CRUD de pessoas físicas, juridicas e reclamações,
                                além de autenticação e autorização de usuários.
                                """)
                        .contact(new Contact()
                                .name("Gustavo Henrique Garayo")
                                .url("https://platform.senior.com.br/senior-x/#/?category=frame&link=https:%2F%2Fhcm.senior.com.br%2F%23%2Fpublic%2Fgustavo.henrique.garayo%2F2DD51272E712471E82B9FC23BA6F080F%3FmillisecondsNow%3D1701885460787%26role%3DEMPLOYEE")
                                .email("gustavo.garayo@senior.com.br")));
    }

}
