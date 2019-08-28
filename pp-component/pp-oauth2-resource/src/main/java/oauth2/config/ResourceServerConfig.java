package oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // 不过滤的资源
    private String[] antMatchers = new String[] {
            //
            "/doc.html",
            //
            "/swagger-resources/**",
            //
            "/v2/api-docs",
            //
            "/oauth/**"
            //
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and().authorizeRequests().antMatchers(antMatchers).permitAll().anyRequest().authenticated();
    }
}