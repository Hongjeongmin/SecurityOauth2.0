package com.naver.client.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
        		.jdbc(dataSource);
// inMemort 로 테스트
//                .inMemory()
//                .withClient("naver-client")
//                .secret(passwordEncoder.encode("naver-client"))
//                .authorizedGrantTypes("password",
//                        "authorization_code",
//                        "refresh_token",
//                        "implicit")
//                .scopes("read", "write", "trust")
//                .redirectUris("http://localhost:8090/test")
//                .accessTokenValiditySeconds(1*60*60) 
//                .refreshTokenValiditySeconds(24*60*60);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
    /*
     * 입력 폼 예시 (이렇게 들어가면 로그인하고 인증코드를 Redirect로 던져준다.)
     * http://localhost:8080/oauth/authorize?client_id=naver-client&redirect_uri=http://localhost:8090/test&response_type=code&scope=read
     */
}
