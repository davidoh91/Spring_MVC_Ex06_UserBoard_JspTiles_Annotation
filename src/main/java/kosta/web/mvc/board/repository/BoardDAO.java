package kosta.web.mvc.board.repository;

import java.util.List;

import kosta.web.mvc.board.dto.ElectronicsDTO;

public interface BoardDAO{
	/**
	  * 레코드 전체 검색
	  * */
	  List<ElectronicsDTO> selectAll() ;
	  
	  /**
		  * 모델번호에 해당하는 레코드 검색
		  * */
	  ElectronicsDTO selectByModelNum(String modelNum) ;
	  
	  /**
	   * 모델번호에 해당하는 레코드 조회수 증가하기
	   * */
	  int readnumUpdate(String modelNum);
	  
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
