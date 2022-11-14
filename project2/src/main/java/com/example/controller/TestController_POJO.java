package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class TestController_POJO implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("여기까지 오는지 확인~!");
		
		ModelAndView mv1 = new ModelAndView(); // 모델과 뷰
		//ModelAndView mv1 = new ModelAndView("test"); // 모델과 뷰. "test" 필요한가?
		mv1.addObject("data1", "스프링 MVC의 모델이에요~!"); // data1은 모델(Model)입니다.
//		mv1.setViewName("/WEB-INF/view/test.jsp"); // test.jsp는 뷰(View)입니다.
		// 경로가 길어서 보기 좋지 않다. 아래 코드 추가하여 뷰리졸버 경로를 알아내고 서블릿에 등록 (dispatcher1-servlet.xml 이용하여 의존성 주입)
		//InternalResourceViewResolver resolver1;
		
		return mv1; // 모델과 뷰를 리턴.
	}

}
