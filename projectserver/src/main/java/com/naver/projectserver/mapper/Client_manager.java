package com.naver.projectserver.mapper;


import com.naver.projectserver.common.Pwd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class Client_manager {
	String id;
	String client_id;
	String client_secret;
	String appname;
	String web_server_redirect_uri;

	public void Create_client_manger(String id,String appname, Pwd pwd,String web_server_redirect_uri) {
		this.id = id;
		this.appname =appname;
		this.client_id = pwd.getRnadomcode(60);
		this.client_secret = pwd.getRnadomcode(60);
		this.web_server_redirect_uri = web_server_redirect_uri;
	}

}
