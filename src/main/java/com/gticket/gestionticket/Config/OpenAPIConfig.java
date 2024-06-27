package com.gticket.gestionticket.Config;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${gticket.openapi.dev-url}")
    private String devUrl;



    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");



        Contact contact = new Contact();
        contact.setEmail("kontere13e@gmail.com");
        contact.setName("KONTERE TIENOU");
        contact.setUrl("#");

        License mitLicense = new License().name("Sous licence MIT ").url("#");

        Info info = new Info()
                .title("Ticket Management API")
                .version("1.0")
                .contact(contact)
                .description("Ce API expose les endpoints pour la gestion de tickets.").termsOfService("#")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}