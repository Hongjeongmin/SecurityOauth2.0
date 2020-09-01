package com.naver.projectserver.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDto {
	@NotEmpty
	private String name;
	private String description;
	private String location; // (optional) 이게 없으면 온라인 모임
	private int basePrice; // (optional)
	private int maxPrice; // (optional)

//	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime beginEventDateTime;
//	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime endEventDateTime;
}
