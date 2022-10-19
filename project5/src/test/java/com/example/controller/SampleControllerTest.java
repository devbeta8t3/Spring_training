package com.example.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.domain.Ticket;
import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration	// 서블릿 콘텍스트를 사용하겠다는 의미
//@ContextConfiguration({"file:src/main/webapp/WEB-INF/root-context.xml",
//						"file:src/main/webapp/WEB-INF/appServlet/servlet-context.xml"})	// 1. xml 설정 사용
@ContextConfiguration(classes= {com.example.config.RootConfig.class,
								com.example.config.ServletConfig.class}) 				// 2. java 설정 사용
@Log4j
public class SampleControllerTest {
	
	////////테스트시 서버 재가동 안하기위한 코드 추가 ///////////////////////////////////////////////
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;	// 가짜 mvc - url과 파라미터를 브라우저 사용처럼 만들어 컨트롤러 실행
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	//////// 테스트시 서버 재가동 안하기위한 코드 끝 ////////////////////////////////////////////////
	
	@Test
	public void testConvert() throws Exception {	// REST 방식 테스트. 별도 프로그램이나 크롬확장 이용
		
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		// GSON 라이브러리를 이용해서 json 데이터로 변환
		String jsonStr = new Gson().toJson(ticket);
		
		log.info(jsonStr);
		
		mockMvc.perform(post("/sample/ticket").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
			   .andExpect(status().is(200));
	}

}
