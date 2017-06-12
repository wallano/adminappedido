package com.restaurante.harley.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.restaurante.harley.controller.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
   @Autowired
   AuthenticationService aauthenticationService;
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
       auth.userDetailsService(aauthenticationService);
 
   }
 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
 
       http.csrf().disable();
 
       http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
               .access("hasAnyRole('EMPLOYEE', 'MANAGER')");
 

       http.authorizeRequests().antMatchers("/product").access("hasRole('MANAGER')");
 

       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
 
       http.authorizeRequests().and().formLogin()//
               .loginProcessingUrl("/j_spring_security_check")
               .loginPage("/login")//
               .defaultSuccessUrl("/accountInfo")//
               .failureUrl("/login?error=true")//
               .usernameParameter("userName")//
               .passwordParameter("password")
               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
 
   }

}
