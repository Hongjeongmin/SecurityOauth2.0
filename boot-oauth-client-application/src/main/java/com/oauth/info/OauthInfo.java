package com.oauth.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class OauthInfo {
	@Value("${custom.client_id}")
	public String client_id;
	@Value("${custom.secret}")
	public String secret;
	@Value("${custom.redirect_uri}")
	public String redirect_uri;
	@Value("${custom.target_uri}")
	public String target_uri;
	
	
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getTarget_uri() {
		return target_uri;
	}
	public void setTarget_uri(String target_uri) {
		this.target_uri = target_uri;
	}
	
	
}
