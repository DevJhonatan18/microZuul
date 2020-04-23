package com.aforo255.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	
	@Value("${config.security.outh.jwt.key}")
	private String jwtkey;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/api/seguridad/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET , "/api/account/listar" , "/api/account/ver/{id}" , "/api/historical/listar" ,
				                      "/api/historical/transaction/{accountId}" , "/api/cronograma/listar", "/api/cronograma/ver/{id}"  
				                    , "/api/cronograma/ver/{id}/{id2}" , "/api/historico/listar",
				                      "/api/historico/transaction/{nroprestamo}/{cuota}" )
		
		.hasAnyRole("ADMIN", "USER").antMatchers("/api/deposit/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/api/payment/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/api/withdrawal/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors().configurationSource(configurationSource());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		// TODO Auto-generated method stub
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(jwtkey);
		return jwtAccessTokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		// TODO Auto-generated method stub

		return new JwtTokenStore(accessTokenConverter());
	}
	
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();		
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("*"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean< CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(configurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	

}
