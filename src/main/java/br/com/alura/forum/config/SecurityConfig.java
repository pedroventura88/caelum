package br.com.alura.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("pedro")
                .password("123")
                .authorities("ROLE_ADMIN")
                .and()
                .withUser("convidado")
                .password("1")
                .authorities("ROLE_CONVIDADO")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
