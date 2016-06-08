package cz.ondrejvelisek.oauth.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import java.util.Arrays;

/**
 * @author Ondrej Velisek <ondrejvelisek@gmail.com>
 */
@Configuration
@EnableOAuth2Client
public class OAuth2Config {

    @Bean
    public OAuth2RestOperations perunOAth2Rest(OAuth2ClientContext oauth2ClientContext) {
        OAuth2RestTemplate template = new OAuth2RestTemplate(perunOAuth2Resource(), oauth2ClientContext);
        return template;
    }

    @Bean
    protected OAuth2ProtectedResourceDetails perunOAuth2Resource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        // Optional. Only some internal Spring id.
        details.setId("github");
        details.setClientId("test_client");
        details.setClientSecret("d3f4e0be-e91c-4571-b29c-969f233b150d");
        details.setUserAuthorizationUri("http://localhost:8080/oauth2/authorize");
        details.setAccessTokenUri("http://localhost:8080/oauth2/token");
        // Requested scopes (Right now do not work)
        details.setScope(Arrays.asList(new String[]{/*"openid"*/}));
        return details;
    }
}
