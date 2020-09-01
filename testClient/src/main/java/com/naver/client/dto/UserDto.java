package com.naver.client.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserDto {
	@NotEmpty
	String id;
	@NotNull
	String pwd;
	@NotNull
	String nickname;
}
