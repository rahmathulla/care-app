package com.ncodea.care.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.ncodea.care.filter.CorsFilter;

@EnableWebSecurity
public class CareAppSecurity extends WebSecurityConfigurerAdapter {
	
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public CareAppSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.csrf().disable().authorizeRequests().antMatchers( HttpMethod.GET, "/care/**", "/",  "/**/*.html",
                "/**/*.{png,jpg,jpeg,svg.ico}",
                "/**/*.css",
                "/**/*.js") .permitAll()
        		.antMatchers(HttpMethod.POST, "/care/user/register")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/care/user/login") .permitAll().
                anyRequest().fullyAuthenticated();
                //.and().httpBasic();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
