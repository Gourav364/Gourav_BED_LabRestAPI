package com.gl.student.configuration;

import com.gl.student.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImplementation();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/addUser", "/resources/**").permitAll()
                .antMatchers("/","/student/save","/student/showFormForAdd","/student/403").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/student/showFormForUpdate","/student/delete").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/student/403")
                .and()
                .cors().and().csrf().disable();
    }
}
