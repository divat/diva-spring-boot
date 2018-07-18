package com.diva.oauth.example.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.diva.oauth.example.config.database.DatabaseConfig;

/**
 * OAuth2 Authorization server
 * Responsible for authenticating and issuing the
 * tokens
 * @author DHIVAKART
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	private final AuthenticationManager authenticationManager;
	private final DatabaseConfig appConfig;

	@Autowired
	public AuthorizationServerConfig(AuthenticationManager authenticationManager, DatabaseConfig appConfig) {
		this.authenticationManager = authenticationManager;
		this.appConfig = appConfig;
	}

	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(appConfig.dataSource());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		/*
		 * Allow our tokens to be delivered from our token access point as well
		 * as for tokens to be validated from this point
		 */
		security.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(appConfig.tokenStore());
	}
}
