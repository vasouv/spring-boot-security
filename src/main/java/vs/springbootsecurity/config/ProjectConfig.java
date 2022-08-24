package vs.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
            .antMatchers("/", "/unsecured").permitAll()
            .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        var userDetailsService = new InMemoryUserDetailsManager();

        var vasouv = User
            .withUsername("vasouv")
            .password(passwordEncoder().encode("vasouv"))
            .authorities("read")
            .build();
        var user123 = User
            .withUsername("user123")
            .password(passwordEncoder().encode("user123"))
            .authorities("read")
            .build();

        userDetailsService.createUser(vasouv);
        userDetailsService.createUser(user123);

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
