package kosta.web.mvc.board.service;

import java.util.List;

import kosta.web.mvc.board.dto.ElectronicsDTO;

public interface BoardService {
	/**
	  * 레코드 전체 검색
	  * */
	  List<ElectronicsDTO> selectAll() ;
	  
	  /**
		  * 모델번호에 해당하는 레코드 검색
		  * @param: state true이면 조회수증가, false이면 조회증가안함.
		  * */
	  ElectronicsDTO selectByModelNum(String modelNum , boolean state) ;
	  
	 
	  
	/**
	 * 레코드 삽입
	 * */
	  int insert(ElectronicsDTO electronics);
	  
	  /**
	   * 모델번호에 해당하는 레코드 삭제
	   * */
	   int delete(String modelNum, String password);
	  
	   /**
	    * 모델번호에 해당하는 레코드 수정
	    * */
	   int update(ElectronicsDTO electronics);
}
