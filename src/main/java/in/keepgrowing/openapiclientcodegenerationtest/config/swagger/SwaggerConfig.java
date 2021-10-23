package in.keepgrowing.openapiclientcodegenerationtest.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    private static final String CODE_GENERATION_PROFILE = "angular";

    private final Environment environment;

    SwaggerConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI customOpenAPI(OpenApiProperties properties) {
        var openApi = new OpenAPI()
                .info(getInfo(properties));

        if (Arrays.asList(environment.getActiveProfiles()).contains(CODE_GENERATION_PROFILE)) {
            var server = getLocalhostServer();
            openApi.setServers(Collections.singletonList(server));
        }

        return openApi;
    }

    private Info getInfo(OpenApiProperties openApiProperties) {
        return new Info()
                .title(openApiProperties.getProjectTitle())
                .description(openApiProperties.getProjectDescription())
                .version(openApiProperties.getProjectVersion());
    }

    private Server getLocalhostServer() {
        var server = new Server();
        server.setUrl("http://localhost:8080");
        return server;
    }
}
