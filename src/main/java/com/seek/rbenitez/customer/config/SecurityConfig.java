package com.seek.rbenitez.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 */

    // Configuracion de seguridad basica
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                    .requestMatchers("/seek/customers/metrics").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and().build();
        //        return httpSecurity
//                //.csrf().disable() // Deshabilita CSRF (Cross-Site Request Forgery) - Vulneribilidad a nivel de formularios | proteccion cuando el servicio interactua con navegadores, ya que la peticion cliente servidor puede ser interceptada y spring security nos protege de ello.
    }

     */

    // Configuracion de seguridad avanzada
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth ->
                        {
                            auth.requestMatchers("/seek/customers/metrics").permitAll();
                            auth.anyRequest().authenticated();
                        })
                .formLogin().permitAll()
                .and()
                .build();
    }
}
