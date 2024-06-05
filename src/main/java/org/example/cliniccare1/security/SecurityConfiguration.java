//    package org.example.cliniccare1.security;
//
//    import jakarta.servlet.Filter;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.http.HttpMethod;
//    import org.springframework.security.authentication.AuthenticationManager;
//    import org.springframework.security.authentication.AuthenticationProvider;
//    import org.springframework.security.config.Customizer;
//    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.config.http.SessionCreationPolicy;
//    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//    import org.springframework.security.crypto.password.PasswordEncoder;
//    import org.springframework.security.web.SecurityFilterChain;
//    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//    import org.springframework.web.cors.CorsConfiguration;
//    import org.springframework.web.cors.CorsConfigurationSource;
//    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//    import java.util.Arrays;
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfiguration {
//
//        private final CustomAuthenticationProvider customAuthenticationProvider;
//
//        @Autowired
//        public SecurityConfiguration(CustomAuthenticationProvider customAuthenticationProvider) {
//            this.customAuthenticationProvider = customAuthenticationProvider;
//        }
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http.csrf()
//                    .disable()
//                    .authorizeHttpRequests(authorize -> authorize
//                            .requestMatchers(HttpMethod.GET,"/api/v1/patients/all").hasAnyRole("ADMIN","DOCTOR")
//                            .requestMatchers(HttpMethod.GET,"/api/v1/patients/{id}").hasAnyRole("PATIENT")
//                            .requestMatchers(HttpMethod.POST,"/api/v1/patients/new").permitAll()
//                            .requestMatchers(HttpMethod.DELETE,"/api/v1/patients/delete/").hasRole("PATIENT")
//                            .requestMatchers(HttpMethod.PUT,"/api/v1/patients/update/").hasRole("PATIENT")
//                            .requestMatchers(HttpMethod.GET,"/api/v1/doctors/all").hasAnyRole("ADMIN","PATIENT","DOCTOR")
//                            .requestMatchers(HttpMethod.GET,"/api/v1/doctors/{id}").hasRole("DOCTOR")
//                            .requestMatchers(HttpMethod.POST,"/api/v1/doctors/new").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.DELETE,"/api/v1/doctors/delete/").hasRole("DOCTOR")
//                            .requestMatchers(HttpMethod.PUT,"/api/v1/doctors/update/").hasRole("DOCTOR")
//                            .requestMatchers(HttpMethod.GET,"/api/v1/admins/all").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers(HttpMethod.GET,"/api/v1/admins/{id}").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers(HttpMethod.POST,"/api/v1/admins/new").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers(HttpMethod.DELETE,"/api/v1/admins/delete/").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers(HttpMethod.PUT,"/api/v1/admins/update/").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers("/user/auth/**").permitAll()
//                            .requestMatchers("/web/**").permitAll()
//                            .requestMatchers("/swagger-ui/**").permitAll()
//                            .requestMatchers("/v3/api-docs").permitAll()
//                            .requestMatchers("/v3/api-docs/**").permitAll()
//                            .requestMatchers("/actuator/**").permitAll()
//                            .requestMatchers(HttpMethod.GET, "/myName").hasRole("CLINIC")
//                            .requestMatchers(HttpMethod.GET, "/api/v1/clinic/{id}").hasRole("CLINIC")
//                            .requestMatchers(HttpMethod.PUT, "/api/v1/clinic/update/{id}").hasRole("CLINIC")
//                            .requestMatchers(HttpMethod.DELETE, "/api/v1/clinic/delete/{id}").hasRole("CLINIC")
//                            .requestMatchers(HttpMethod.GET, "/api/v1/clinic/all").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.POST, "/api/v1/clinic/new").permitAll()
//                            .requestMatchers("/clinic/auth/**").permitAll()
//                            .requestMatchers("/web/**").permitAll()
//                            .requestMatchers("/swagger-ui/**").permitAll()
//                            .requestMatchers("/v3/api-docs").permitAll()
//                            .requestMatchers("/v3/api-docs/**").permitAll()
//                            .requestMatchers("/actuator/**").permitAll()
//                            .requestMatchers("/api/v1/dashboard/roles/new_role").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers("/api/v1/dashboard/roles/delete_role").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers("/api/v1/dashboard/roles/all").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers("/api/v1/dashboard/roles/update_role").hasAnyRole("ADMIN","CLINIC")
//                            .requestMatchers("/web/**").permitAll()
//                            .requestMatchers("/swagger-ui/**").permitAll()
//                            .requestMatchers("/v3/api-docs").permitAll()
//                            .requestMatchers("/v3/api-docs/**").permitAll()
//                            .requestMatchers("/actuator/**").permitAll()
//
//                    ).httpBasic(Customizer.withDefaults())
//
//
//                    .sessionManagement()
//                    .and()
//                    .formLogin().disable();
//
//            return http.build();
//        }
//
//        @Bean
//        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//            AuthenticationManagerBuilder authenticationManagerBuilder =
//                    http.getSharedObject(AuthenticationManagerBuilder.class);
//            authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
//            return authenticationManagerBuilder.build();
//        }
//
//
//
//
//        @Bean
//        CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Use allowedOriginPatterns instead of allowedOrigins
//            configuration.setAllowedMethods(Arrays.asList("*"));
//            configuration.setAllowedHeaders(Arrays.asList("*"));
//            configuration.setAllowCredentials(true); // If you need credentials (e.g., cookies), set this to true
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
//
//        @Bean
//        public PasswordEncoder passwordEncoder(){
//            return new BCryptPasswordEncoder(); //it mean no password encoding
//
//        }
//
//    }
