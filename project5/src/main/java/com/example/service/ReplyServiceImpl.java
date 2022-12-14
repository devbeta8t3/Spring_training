package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Criteria;
import com.example.domain.ReplyPageDTO;
import com.example.domain.ReplyVO;
import com.example.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor 대신 @Setter 사용
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		
		log.info("register......" +vo);
		return mapper.insert(vo);
		
	}

	@Override
	public ReplyVO get(Long rno) {
		
		log.info("get......" +rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO board) {
		
		log.info("modify......" +board);
		return mapper.update(board);
		
	}

	@Override
	public int remove(Long rno) {
		
		log.info("remove......" +rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		
		log.info("getList with criteria: " +cri);
		log.info("get Reply List of a Board # " + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		
		return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
	}



}
