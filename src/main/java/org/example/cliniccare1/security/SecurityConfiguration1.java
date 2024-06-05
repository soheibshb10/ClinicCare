package org.example.cliniccare1.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration1 {

    private final CustomAuthenticationProvider customAuthenticationProvider;



    public SecurityConfiguration1(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/api/v1/patients/all").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/patients/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/patients/new").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/patients/delete/").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/patients/update/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/doctors/all").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/doctors/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/doctors/new").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/doctors/delete/").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/doctors/update/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/admins/all").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/admins/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/admins/new").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/admins/delete/").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/admins/update/").permitAll()
                        .requestMatchers("/user/auth/**").permitAll()
                        .requestMatchers("/web/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/myName").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/clinic/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/clinic/update/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/clinic/delete/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/clinic/all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/clinic/new").permitAll()
                        .requestMatchers("/clinic/auth/**").permitAll()
                        .requestMatchers("/web/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/v1/dashboard/roles/new_role").permitAll()
                        .requestMatchers("/api/v1/dashboard/roles/delete_role").permitAll()
                        .requestMatchers("/api/v1/dashboard/roles/all").permitAll()
                        .requestMatchers("/api/v1/dashboard/roles/update_role").permitAll()
                        .requestMatchers("/web/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()

                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }




    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Use allowedOriginPatterns instead of allowedOrigins
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); // If you need credentials (e.g., cookies), set this to true

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //it mean no password encoding

    }

}
