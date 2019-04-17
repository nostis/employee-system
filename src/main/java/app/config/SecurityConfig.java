package app.config;

import app.dao.UserDAOCrud;
import app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserDAOCrud.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.logout();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**", "/admin").access("(hasRole('ADMIN')) and isAuthenticated()")
                .antMatchers("/accountant/**", "/accountant").access("(hasRole('ACCOUNTANT')) and isAuthenticated()")
                .antMatchers("/employee/**", "/employee").access("(hasRole('EMPLOYEE')) and isAuthenticated()")
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin().permitAll()
                .successHandler(authSuccessHandler());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationSuccessHandler authSuccessHandler(){
        return new SimpleAuthHandler();
    }
}
