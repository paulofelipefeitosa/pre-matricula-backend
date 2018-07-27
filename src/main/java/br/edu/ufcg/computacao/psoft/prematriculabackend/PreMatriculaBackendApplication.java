package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableOAuth2Sso
@Controller
public class PreMatriculaBackendApplication extends WebSecurityConfigurerAdapter {

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource,
            OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }

    @Bean
    public AuthoritiesExtractor authoritiesExtractor(OAuth2RestOperations template) {
        return map -> {
            ArrayList<HashMap<String, String>> emailProperty =
                    (ArrayList<HashMap<String, String>>) map.get("emails");
            HashMap<String, String> emailValue = emailProperty.get(0);
            String email = emailValue.get("value");
            if (email.contains("@ccc.ufcg.edu.br")) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            } else {
                throw new BadCredentialsException("Not in Spring Team");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").failureUrl("/unauthenticated").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
    }

    @Configuration
    protected static class ServletCustomizer {
        @Bean
        public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer() {
            return container -> {
                container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/unauthenticated"));
            };
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PreMatriculaBackendApplication.class, args);
    }
}
