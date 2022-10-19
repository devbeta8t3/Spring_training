package com.example.domain;

import lombok.Data;

@Data
public class Ticket {	// @RequestBody 예제
	
	private int tno;
	private String owner;
	private String grade;

}
