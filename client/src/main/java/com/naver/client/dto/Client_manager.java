package com.naver.client.dto;

import com.naver.client.Pwd;

public class Client_manager {
	String id;
	String client_id;
	String client_secret;
	String appname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public void Create_client_manger(String id,String appname, Pwd pwd) {
		this.id = id;
		this.appname =appname;
		this.client_id = pwd.getRnadomcode(60);
		this.client_secret = pwd.getRnadomcode(60);
	}

	@Override
	public String toString() {
		return "Client_manager [id=" + id + ", client_id=" + client_id + ", client_secret=" + client_secret
				+ ", appname=" + appname + "]";
	}

}
