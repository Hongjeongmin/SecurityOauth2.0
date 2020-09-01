package com.naver.projectserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
	/*
	 * resource서버 ID 서버아이디
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("HJWresource").stateless(false);

	}
	/*
	 * 익명 설정을 사용하지 않는다. api 서버에 대한 접근은 인증이 필러로 한다. 접근 거절에 대해서는
	 * OAuth2AccessDeniedHandler를 사용한다.
	 */

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.anonymous().disable()
			.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and()
			.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());

//		http.requestMatchers().antMatchers("/api/read/**")
//			.and()
//		.authorizeRequests().anyRequest().access("#oauth2.hasScope('read')");
//		http.authorizeRequests().antMatchers("/api/read/**").access("#oauth2.hasScope('read')");
//		http.authorizeRequests().antMatchers("/api/write/**").access("#oauth2.hasScope('write')");
		
//		http.requestMatchers().antMatchers("/api/write/**").and().authorizeRequests().anyRequest()
//				.access("#oauth2.hasScope('write')");
//		http.requestMatchers().antMatchers("/api/read/**").and().authorizeRequests().anyRequest()
//				.access("#oauth2.hasScope('read')");
//		http.requestMatchers().antMatchers("/api/users/**").and().authorizeRequests().anyRequest().access("#oauth2.hasScope('trust')");
//		
//		http.cors().disable();
//		
	}

}
