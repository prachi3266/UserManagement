/*It does not Verify login, it checks only if to access a particular endpoint, is it must to be logged in*/

package com.example.UserManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
            CustomAccessDeniedHandler customAccessDeniedHandler)
            throws Exception{

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/users/*/role").hasRole("ADMIN")
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception-> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(form->form.disable())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


/*    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user= User.builder()
                .username("Prachi")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

}
