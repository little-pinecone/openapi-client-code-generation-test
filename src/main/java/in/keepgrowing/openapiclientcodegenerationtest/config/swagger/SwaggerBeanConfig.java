package in.keepgrowing.openapiclientcodegenerationtest.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class SwaggerBeanConfig {

    /**
     * The reason for this class is to provide a custom HttpMessageConverter to deserialize
     * application/octet-stream bodies using Jackson.
     *
     * @see <a href="https://github.com/swagger-api/swagger-ui/issues/6462#issuecomment-929189296">support custom content-type selection</a>
     */
    @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(new MediaType("application", "octet-stream")));
        return converter;
    }
}
