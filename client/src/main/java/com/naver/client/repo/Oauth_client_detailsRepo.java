package com.naver.client.repo;

import com.naver.client.dto.Oauth_client_details;

public interface Oauth_client_detailsRepo {
	boolean insert(Oauth_client_details ocd);
	Oauth_client_details select();
}
