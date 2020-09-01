package com.naver.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
	String id;
	String pwd;
	String nicknamel;
}
