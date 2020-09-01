package com.naver.projectserver.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@Builder
@NoArgsConstructor@AllArgsConstructor@ToString
public class EventDto {
	@NotEmpty
	private String name;
	@NotNull
	private String description;
	private String location; // (optional) 이게 없으면 온라인 모임
	@Min(0)
	private int basePrice; // (optional)
	@Min(0)
	private int maxPrice; // (optional)
	private LocalDateTime beginEventDateTime;
	private LocalDateTime endEventDateTime;
}
