package com.naver.client.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Oauth_client_details {

	private String client_id;
	private String resource_ids;
	private String client_secret;
	private String scope;
	private String authorized_grant_types;
	private String web_server_redirect_uri;
	private String authorities;
	private String access_token_validity;
	private String refresh_token_validity;
	private String additional_information;
	private String autoapprove;

	public Oauth_client_details() {
		this.access_token_validity = "3600";
		this.refresh_token_validity = "21600";
		this.autoapprove = "false";
	}

	public void encodeClient_secret(PasswordEncoder passwordEncoder) {
		this.client_secret = passwordEncoder.encode(this.client_secret);
	}

}
