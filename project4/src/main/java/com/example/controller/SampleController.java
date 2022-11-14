package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpHeaders;		// http: method - post, get 방식
import org.springframework.http.HttpStatus;			// 있는지 없는지
import org.springframework.http.ResponseEntity;		//
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;	// 객체
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.SampleDTO;
import com.example.domain.SampleDTOList;
import com.example.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")	// 1차 맵핑
@Log4j
public class SampleController {
	
	@RequestMapping("")			// 2차 맵핑
	@Test
	public void basic() {
		
		log.info("basic...............");
	}
	
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		
		log.info("basic get....................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		
		log.info("basic get only get..................");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info("" +dto);
		return "ex01";	// /WEB-INF/view/ex01.jsp 로 찾아간다. (VIEW)
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name: " +name);
		log.info("age: " +age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {	// ids를 배열로 받는다.
		
		log.info("ids: " +ids);
		return "ex02List";
	}

	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids")String[] ids) {	// ids를 배열로 받는다.
		
		log.info("array ids: " +ids /*Arrays.toString(ids)*/);
		return "ex02Array";
	}

	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {	// SampleDTOList 패턴 진짜 많이 쓴다.
													// [ -> %5B  , ] -> %5D
		log.info("list dtos: " +list);
		return "ex02Bean";
	}
	
	// TodoDTO 에서 날짜 패턴(@DateTimePattern)을 지정해놨기 때문에 아래 코드는 굳이 쓸 필요없다. 
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo: " +todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " +dto);
		log.info("page: " +page);
		return "/sample/ex04";		// view/sample/ex04.jsp로 브라우저에 출력됨
	}
	
	@GetMapping("/ex05")
	public void ex05() {	// 맵핑과 리턴이 같을땐 그냥 void 해도 된다.
		
		log.info("/ex05.......................");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {	// 객체를 JSON 형태로 html 바디에 응답해준다.
		
		log.info("/ex06......................");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;	// 객체를 반환하면 JSON 형태로 응답 @ResponseBody
	}
	
	@GetMapping("/fav")
	public @ResponseBody SampleDTOList fav(String u_id) {	// 객체를 JSON 형태로 html 바디에 응답해준다.
		
		log.info("/fav......................");
		SampleDTO dto1 = new SampleDTO();
		SampleDTO dto2 = new SampleDTO();
		SampleDTO dto3 = new SampleDTO();
		
		dto1.setAge(2);
		dto1.setName(u_id);
		
		
		SampleDTOList dtoList = new SampleDTOList();
		//dtoList.addElements(dto1);
		//dtoList.addElement()
		
		
		return dtoList;	// 객체를 반환하면 JSON 형태로 응답 @ResponseBody
	}
	
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		
		log.info("/ex06......................");
		// { "name": "강감찬" }
		String msg = "{\"name\": \"강감찬\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("-----------------------------");
			log.info("name: " +file.getOriginalFilename());
			log.info("size: " +file.getSize());
		});
	}
	
	
}