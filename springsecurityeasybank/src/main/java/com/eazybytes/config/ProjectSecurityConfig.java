package com.eazybytes.config;

import com.eazybytes.filter.AuthorityLoggingAfterFilter;
import com.eazybytes.filter.AuthorityLoggingAtFilter;
import com.eazybytes.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain_copy_from_default_spring_implementation(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin();
//        http.httpBasic();
//
//        return http.build();
//    }


//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain_deny_all(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();
//
//        return http.build();
//    }


//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain_permit_all(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .and().httpBasic();
//
//        return http.build();
//    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
//                .csrf().disable()// Temporary disable csrf
                .csrf().ignoringAntMatchers("/contact", "/register").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())


                .and().addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthorityLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthorityLoggingAfterFilter(), BasicAuthenticationFilter.class)


                .authorizeRequests()
                .antMatchers("/account/details", "/account/my-balance", "/account/my-cards", "/my-loans", "/user").authenticated()
                .antMatchers("/register", "/notices", "/contact").permitAll()
                .and().httpBasic()
                .and().formLogin();

        return http.build();
    }


//    @Bean
//    // Approach 1 create hard coded users
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }


//    @Bean
    // Approach 2 create hard coded users, with custom PasswordEncoder
//    public InMemoryUserDetailsManager userDetailsServiceCustomPasswordEncoder() {
//        UserDetails admin = User.withUsername("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // plain text
        return new BCryptPasswordEncoder();
    }
}
