package ad.GestionCatering.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestion de Catering")
                        .version("1.0")
                        .description("Documentación  Gestion de Catering")
                        .contact(new Contact()
                                .name("Rodrigo GB")
                                .email("soporte@gmail.com")
                                .url("https://www.tienda.com")));
    }
}
