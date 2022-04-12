package com.letscode.Ecommerce.infra.config;

import com.letscode.Ecommerce.infra.auth.AutenticacaoEntryPoint;
import com.letscode.Ecommerce.infra.filter.AutorizacaoFilter;
import com.letscode.Ecommerce.service.UsuarioDetalheService;
import org.apache.catalina.filters.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoEntryPoint autenticacaoEntryPoint;

    @Autowired
    private AutorizacaoFilter autorizacaoFilter;

    @Autowired
    private UsuarioDetalheService usuarioDetalheService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // desabilita CSRF (cross script)
        http.csrf().disable()
                // Libera o endpoint de autorização de ser autenticado
                .authorizeRequests()
                .antMatchers("/login", "/login/")
                .permitAll()
                // Todas as outras requisições precisam ser autenticadas
                .anyRequest().authenticated().and()
                // Exception handler do Spring Security
                .exceptionHandling().authenticationEntryPoint(autenticacaoEntryPoint)
                // Sessão da API é stateless (não armazena estado)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Adiciona web filter para autenticação. Irá atuar como uma barreira que impedirá
        // que autenticações inválidas sequer entrem no fluxo da nossa aplicação (Controller).
        http.addFilterBefore(autorizacaoFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetalheService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}
