package com.niall.bankserver.config;

import com.niall.bankserver.enums.AccountType;
import com.niall.bankserver.jwt.JwtConfigurer;
import com.niall.bankserver.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/authentication/**").permitAll()
                .antMatchers("/account/register").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/transaction/createforaccount").hasRole(AccountType.BUSINESS_ACCOUNT.name())
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger*").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(this.authenticationManager(), jwtTokenProvider));
    }
}
