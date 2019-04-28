//package com.arangodb.spring.demo.mitre;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableOAuth2Client
//public class MitreOpenIdConnectConfig {
//    @Value("${mitre.clientId}")
//    private String clientId;
//
//    @Value("${mitre.clientSecret}")
//    private String clientSecret;
//
//    @Value("${mitre.accessTokenUri}")
//    private String accessTokenUri;
//
//    @Value("${mitre.userAuthorizationUri}")
//    private String userAuthorizationUri;
//
//    @Value("${mitre.redirectUri}")
//    private String redirectUri;
//
//    @Bean
//    public OAuth2ProtectedResourceDetails googleOpenId() {
//        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
//        details.setClientId(clientId);
//        details.setClientSecret(clientSecret);
//        details.setAccessTokenUri(accessTokenUri);
//        details.setUserAuthorizationUri(userAuthorizationUri);
//        details.setScope(Arrays.asList("openid", "email"));
//        details.setPreEstablishedRedirectUri(redirectUri);
//        details.setUseCurrentUri(false);
//        return details;
//    }
//
//    @Bean
//    public OAuth2RestTemplate googleOpenIdTemplate(OAuth2ClientContext clientContext) {
//        return new OAuth2RestTemplate(googleOpenId(), clientContext);
//    }
//}
