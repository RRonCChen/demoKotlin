package ron.example.demo.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Todo Api")
                    .description("Todo Api Demo")
                    .version("v1.0")
            )
            .externalDocs(
                ExternalDocumentation().description("github source code")
                    .url("https://github.com/RRonCChen/demoKotlin")
            )
    }

}