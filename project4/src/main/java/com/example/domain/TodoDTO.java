package com.example.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 멤버변수 String title, Date dueDate (패턴:yyyy/MM/dd)
 * 
 */
@Data
public class TodoDTO {
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
