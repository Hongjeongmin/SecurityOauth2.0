package com.naver.client.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.client.common.TestDescription;
import com.naver.client.dto.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	MockMvc MockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	@TestDescription("정상적으로 유저를 생성하는 테스트")
	public void createUser() throws Exception {
		User user = User.builder()
				.id("spring Test id")
				.pwd("Spring test pwd")
				.nickname("Spring Test nickname")
				.build();

		MockMvc.perform(post("/api/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON)
				.content(objectMapper.writeValueAsBytes(user)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("id").exists());
		
		
	}
	
	@Test
	@TestDescription("유저를 생성하기 위해서 필요한 파라미터가 모잘할 경우 발생하는 테스트")
	public void createUserBadRequest() throws Exception{
		User user = User.builder()
				.id("spring Test id")
				.build();

		MockMvc.perform(post("/api/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON)
				.content(objectMapper.writeValueAsBytes(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$[0].objectName").exists())
		.andExpect(jsonPath("$[0].defaultMessage").exists())
		.andExpect(jsonPath("$[0].code").exists());
	}
	
	@Test
	@TestDescription("전체 유저를 조회하는 테스트")
	public void searchUsers() throws Exception {
		
		MockMvc.perform(get("/api/users/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	


}
