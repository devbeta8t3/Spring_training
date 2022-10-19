package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Criteria;
import com.example.domain.ReplyPageDTO;
import com.example.domain.ReplyVO;
import com.example.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * "Reply Controller" Design
 * 
 * Work		URL							TX-Method
 * 등록		/replies/new				POST
 * 조회		/replies/:rno				GET
 * 삭제		/replies/:rno				DELETE
 * 수정		/replies/:rno				PUT or PATCH
 * 페이지		/replies/pages/:bno/:page	GET
 *
 */
@RequestMapping("/replies/")
@Log4j
@RestController
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	// 댓글 등록 (POST)
	// 클라이언트(브라우저)는 JSON 타입으로 된 댓글 데이터를 전송하고,
	// 서버는 댓글의 처리 결과가 정상적으로 되었는지 문자열로 결과를 알려주는 방식으로 처리
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("ReplyVO: " + vo);
		
		int insertCount = service.register(vo);
		log.info("Reply INSERT COUNT: " + insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	// 특정 게시물의 댓글 목록 (GET 방식 요청, xml/json 응답)
	// 페이징 처리 위해 수정
	@GetMapping(value = "/pages/{bno}/{page}", 
				produces = { MediaType.APPLICATION_XML_VALUE,
							 MediaType.APPLICATION_JSON_UTF8_VALUE })
//	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page,
//												 @PathVariable("bno") Long bno) {
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page,
												@PathVariable("bno") Long bno) {
		
		log.info("getList.................");
		
		Criteria cri = new Criteria(page, 10);
		log.info("get Reply List bno: " +bno);
		log.info("cri: " +cri);
		
//		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	// 댓글 조회 (GET)
	@GetMapping(value = "/{rno}", 
				produces = { MediaType.APPLICATION_XML_VALUE,
							 MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		log.info("get: " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	// 댓글 삭제 (DELETE)
	@DeleteMapping(value= "/{rno}" ,produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("remove: " + rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
										: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 수정 (PUT/PATCH)
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
					value = "/{rno}",
					consumes = "application/json",
					produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, 
										 @PathVariable("rno") Long rno) {
		vo.setRno(rno);
		
		log.info("rno: " +rno);
		log.info("modify: " +vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
									   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
