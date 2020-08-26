package com.naver.client.outhservice;

import com.naver.client.dto.Oauth_client_details;

public interface Oauth_client_detailsService {
	boolean insert(Oauth_client_details ocd);
	Oauth_client_details select();
}
