package com.example.refmonolithicserver.configuration.web;

import com.example.refmonolithicserver.common.component.AppProperties.Jwt;
import com.example.refmonolithicserver.configuration.jwt.JwtAuthenticationFilter;
import com.example.refmonolithicserver.configuration.jwt.JwtAuthorizationFilter;
import com.example.refmonolithicserver.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAccessDeniedHandler handler;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final WebConfig webConfig;
    private final Jwt jwt;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChainConfig(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()

                .apply(new MyCustomDsl(webConfig))
                .and()

                .exceptionHandling()
                .accessDeniedHandler(handler)
                .authenticationEntryPoint(entryPoint)
                .and()

                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/signin").permitAll()
                .requestMatchers("/signup").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                .anyRequest().hasRole("USER");

        return http.build();
    }

    @RequiredArgsConstructor
    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

        private final WebConfig webConfig;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(webConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, jwt))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository, jwt));
        }
    }
}
