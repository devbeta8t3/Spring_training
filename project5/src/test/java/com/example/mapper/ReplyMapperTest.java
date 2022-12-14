package com.example.mapper;


import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Criteria;
import com.example.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {com.example.config.RootConfig.class})
@Log4j
public class ReplyMapperTest {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
	// Test for INSERT
	private Long[] bnoArr = { 20L, 21L, 22L, 23L, 24L};
//	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	// Test for READ
//	@Test
	public void testRead() {
		Long targetRno = 21L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	// Test for DELETE
//	@Test
	public void testDelete() {
		Long targetRno = 30L;
		
		mapper.delete(targetRno);
	}
	
	// Test for UPDATE
//	@Test
	public void testUpdate() {
		Long targetRno = 21L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Reply");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT : " + count);
	}
	
	// Test for LIST
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		// 3145745L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
}
