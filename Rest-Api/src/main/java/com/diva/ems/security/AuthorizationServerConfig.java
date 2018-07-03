package com.diva.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	private TokenStore tokenStore = new InMemoryTokenStore();
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints){
		endpoints
		.tokenStore(this.tokenStore)
		.authenticationManager(new CustomAuthenticationManager())
		.userDetailsService(userDetailsService);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
			.withClient("ems-client")
			.authorizedGrantTypes("password")
			.scopes("read", "write")
			.secret("secret")
			.accessTokenValiditySeconds(60)
			.refreshTokenValiditySeconds(60);
	}
	
	@Bean
	public DefaultTokenServices tokenServices(){
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(this.tokenStore);
		return tokenServices;
	}
	
}
