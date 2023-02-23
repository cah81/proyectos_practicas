package com.formacion.BS7_2.security.config;

import com.formacion.BS7_2.security.filters.CustomAuthenticationFilter;
import com.formacion.BS7_2.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig {
  // estas lineas se usaron cuando se tenia la extends de webConfigurationAdapter
 //   private final UserDetailsService userDetailsService;
 //   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationConfiguration authenticationConfiguration;
    // estas lineas se usaron cuando se tenia la extends de webConfigurationAdapter
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        super.configure(auth);
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(authenticationConfiguration));
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/person/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(GET,"/person/personList/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(POST,"/person/addPerson/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST,"/roles/addRole/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/person/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/roles/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/update/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/roles/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/roles/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean

    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
