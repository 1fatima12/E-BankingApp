package com.example.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{
   @Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth
       .inMemoryAuthentication()
       //We can add password storage format, which is {noop} for plain text passwords.
       .withUser("admin").password("{noop}1234").roles("ADMIN","USER");
	   auth
       .inMemoryAuthentication()
       .withUser("user").password("{noop}1234").roles("USER");
}
   @Override
	protected void configure(HttpSecurity http) throws Exception {
	   http.formLogin();
	   http.authorizeRequests().antMatchers("/index","/consulterCompte").hasRole("USER");
	   http.authorizeRequests().antMatchers("/saveOperation","/ListComptes").hasRole("ADMIN");
       http.exceptionHandling().accessDeniedPage("/403");
   }
}
