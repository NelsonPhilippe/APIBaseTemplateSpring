package fr.nelson.apibasetemplate.config;

import fr.nelson.apibasetemplate.enumerations.SecurizedRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain bookFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider())
                .formLogin(form -> form.loginPage("/auth/login"))
                .httpBasic();

        for(SecurizedRoutes route : SecurizedRoutes.values()){
            if(route.isSecurized()){

                if(route.hasRole()){
                    http.authorizeHttpRequests().requestMatchers(route.getRoute()).hasRole(route.getRoles().getRoleName());
                    continue;
                }
                http.authorizeHttpRequests().requestMatchers(route.getRoute()).authenticated();

                continue;
            }

            http.authorizeHttpRequests().requestMatchers(route.getRoute()).permitAll();

        }

//        SecurizedRoutes[] routes = Arrays.stream(SecurizedRoutes.values()).filter(route -> route.getRoles().getRoleName() != null).toArray(SecurizedRoutes[]::new);

//        for(SecurizedRoutes route : routes){
//            http.authorizeHttpRequests().requestMatchers(route.getRoute()).hasRole(route.getRoles().getRoleName());
//        }

        return http.build();
    }

    @Bean
    @Order(3)
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    @Order(2)
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(4)
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
