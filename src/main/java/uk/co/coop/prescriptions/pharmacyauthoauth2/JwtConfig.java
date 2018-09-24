package uk.co.coop.prescriptions.pharmacyauthoauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JwtConfig {
    @Bean
    public JwtAccessTokenConverter jwsTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public JwtTokenStore jwsTokenStore(JwtAccessTokenConverter accessTokenConverter) {
        return new JwtTokenStore(accessTokenConverter);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        List<TokenEnhancer> enhancers = Arrays.asList(jwsTokenConverter());
        tokenServices.setTokenStore(jwsTokenStore(jwsTokenConverter()));
        tokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setTokenEnhancer(converter);
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(enhancers);
        tokenServices.setTokenEnhancer(enhancerChain);
        return tokenServices;
    }
}
