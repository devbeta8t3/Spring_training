package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVO;
import com.example.domain.Criteria;

public interface BoardMapper {

//	@Select("select * from tbl_board where bno > 0")	// 주석처리하고 xml로 옮기자.
//	public List<BoardVO> getList();		// 따로 구현하지 않아도 위의 annotation으로 인해 메소드 사용가능.

	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public Integer insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
}
