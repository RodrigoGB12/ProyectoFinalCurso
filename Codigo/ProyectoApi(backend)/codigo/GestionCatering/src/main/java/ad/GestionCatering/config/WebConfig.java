package ad.GestionCatering.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Applying CORS Configuration =======================================================================");
        registry.addMapping("/**")  // Aplica CORS para todos los endpoints
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes desde Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);  // Permite cualquier cabecera
    }
}
