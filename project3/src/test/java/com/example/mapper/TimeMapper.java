package com.example.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT sysdate FROM dual")
	public String getTime();	// interface 이므로 구현해야한다. @Select로 바로 구현했음.
	
	public String getTime2();	// interface 이므로 구현해야한다. resource에서 찾아라. TimeMapper.xml에 있음.
}
