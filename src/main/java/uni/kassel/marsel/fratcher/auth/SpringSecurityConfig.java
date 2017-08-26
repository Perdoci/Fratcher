package uni.kassel.marsel.fratcher.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow everything. By default, spring-security prevents access and in particular does not allow POST
        // requests without a specific token . By using this configuration we
        // allow everything, since we'll do our security by ourselves.
        http.antMatcher("*").anonymous().and().csrf().disable();
    }
}
