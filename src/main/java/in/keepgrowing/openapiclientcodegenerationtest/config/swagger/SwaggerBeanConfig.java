package in.keepgrowing.openapiclientcodegenerationtest.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;

@Configuration
public class SwaggerBeanConfig {

    /**
     * The reason for this class is to provide a custom HttpMessageConverter to deserialize
     * application/octet-stream bodies using Jackson.
     *
     * @see <a href="https://github.com/swagger-api/swagger-ui/issues/6462">support custom content-type selection</a>
     */
    public SwaggerBeanConfig(MappingJackson2HttpMessageConverter converter) {
        var supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(new MediaType("application", "octet-stream"));
        converter.setSupportedMediaTypes(supportedMediaTypes);
    }
}
