package com.notesapp.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {@Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  };
  
	/*
	 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); };
	 */
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  
	  
	  http
	  .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .httpBasic();
	  
		/*
		 * http.authorizeRequests().anyRequest().hasAnyRole("USER_ALLOWED", "USER")
		 * .and() .logout().permitAll().logoutSuccessUrl("/login") .and()
		 * .csrf().disable();
		 */
  }}
