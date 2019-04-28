package com.arangodb.spring.demo.security;

//import com.arangodb.spring.demo.mitre.OpenIdConnectFilter;
import com.arangodb.spring.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.ManagedBean;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TestSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;
//    @Autowired
//    private OAuth2RestTemplate restTemplate;
//
//    @Bean
//    public OpenIdConnectFilter openIdConnectFilter() {
//        OpenIdConnectFilter filter = new OpenIdConnectFilter("/mitre_login");
//        filter.setRestTemplate(restTemplate);
//        return filter;
//    }

//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http. //addFilterAfter(new OAuth2ClientContextFilter(),
                 //AbstractPreAuthenticatedProcessingFilter.class)
                 //.addFilterAfter(openIdConnectFilter(),
                   //      OAuth2ClientContextFilter.class)
                  //.httpBasic()
                 //.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/mitre_login"))
                 //.and()
                 httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAuthority("USER").
                 and().authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasAuthority("USER")
                 .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/**").hasAuthority("USER")
                 .and().authorizeRequests().antMatchers("/**").hasAuthority("ADMIN")
                 .and().csrf().disable().headers().frameOptions().disable();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
