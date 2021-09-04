package com.claucio.dev.devdojospring.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe de configuração para que o spring security seja usado com as minhas credenciais e não
 * com o password que o spring gera automaticamente.
 */
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * metodo configure é para que todas as requisiçoes feita nas url que estão dentro do controller
     * tem que ter um token de acesso.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Método que configura um novo usuário
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoder => " + passwordEncoder.encode("123"));

        auth.inMemoryAuthentication()
                .withUser("claucio")
                .password(passwordEncoder.encode("123"))
                .roles("USER","ADMIN")
                .and()
                .withUser("william")
                .password(passwordEncoder.encode("321"))
                .roles("USER");

    }
}
