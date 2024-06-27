package com.gticket.gestionticket.Config;


import com.gticket.gestionticket.service.userServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/users/**").hasRole("Admin")
                        .requestMatchers("/api/users/list").hasAnyRole("Admin","Formateur", "Apprenant")
                        .requestMatchers("/api/db/**").hasAnyRole("Admin", "Formateur")
                        .requestMatchers("/api/db/list-db").hasAnyRole("Admin", "Formateur", "Apprenant")
                        .requestMatchers("/api/Notification/**").hasRole("Formateur")
                        .requestMatchers("/api/Notification/list-notication").hasAnyRole("Admin","Formateur", "Apprenant")
                        .requestMatchers("/api/tickets/").hasRole("Apprenant")
                        .requestMatchers("/api/tickets/list-tickets").hasAnyRole("Admin", "Formateur", "Apprenant")
                        .anyRequest().fullyAuthenticated()
                )
                .httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public UserDetailsService UserDetailsService(userServiceImpl userServiceImpl){
        UserDetails apprenant = User.withDefaultPasswordEncoder()
                .username("AAA")
                .password("a-123")
                .roles("Apprenant")
                .build();
        UserDetails formateur = User.withDefaultPasswordEncoder()
                .username("FFF")
                .password("f-123")
                .roles("Formateur")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ADD")
                .password("a-123")
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(apprenant, admin, formateur);
    }


    }

