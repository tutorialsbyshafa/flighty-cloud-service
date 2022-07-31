package com.tutorials.msbooking.config;


import static com.tutorials.msbooking.util.Constants.ADMIN;
import static com.tutorials.msbooking.util.Constants.BOOKINGS_URL;

import com.tutorials.msbooking.security.AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/actuator/**"
    };
    private final AuthorizationFilter authorizationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, BOOKINGS_URL).hasRole(ADMIN)
                .antMatchers(HttpMethod.PUT, BOOKINGS_URL).hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, BOOKINGS_URL).authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
