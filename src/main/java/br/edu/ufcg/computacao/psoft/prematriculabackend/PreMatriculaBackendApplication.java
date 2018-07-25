package br.edu.ufcg.computacao.psoft.prematriculabackend;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
@EnableOAuth2Client
public class PreMatriculaBackendApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    public static void main(String[] args) {
        SpringApplication.run(PreMatriculaBackendApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll().anyRequest()
                .authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter gmailFilter =
                new OAuth2ClientAuthenticationProcessingFilter("/login");

        OAuth2RestTemplate facebookTemplate =
                new OAuth2RestTemplate(gmail(), this.oauth2ClientContext);

        gmailFilter.setRestTemplate(facebookTemplate);

        UserInfoTokenServices tokenServices =
                new UserInfoTokenServices(gmailResource().getUserInfoUri(), gmail().getClientId());

        tokenServices.setRestTemplate(facebookTemplate);

        gmailFilter.setTokenServices(tokenServices);

        return gmailFilter;
    }

    /**
     * Support redirects from app to OAuth2 server. 
     * 
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration =
                new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    /**
     * Get gmail resource properties from application.yaml file.
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties("gmail.resource")
    public ResourceServerProperties gmailResource() {
        return new ResourceServerProperties();
    }

    /**
     * Get gmail client properties from application.yaml file.
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties("gmail.client")
    public AuthorizationCodeResourceDetails gmail() {
        return new AuthorizationCodeResourceDetails();
    }
}
