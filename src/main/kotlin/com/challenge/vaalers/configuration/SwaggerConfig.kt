package com.challenge.vaalers.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig  {

    @Bean
    fun customOpenApi(
        @Value("\${appdescription}") appDescription: String?,
        @Value("\${appversion}") appVersion: String?
    ): OpenAPI? {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("Invitations API ")
                    .version(appVersion)
                    .description(appDescription)
                    .termsOfService("http://swagger.io/terms/")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }


}