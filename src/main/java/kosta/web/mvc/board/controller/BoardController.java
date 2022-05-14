package kosta.web.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.board.dto.ElectronicsDTO;
import kosta.web.mvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService boardService;
	
	private static final String PATH_SAVE="C:\\Edu\\Spring\\fileSave";
	
	/**
	 * 전체검색
	 * */
	@RequestMapping("/list")
	public void list(/*HttpSession session ,*/ Model model) {
		//서비스 호출해서 전체게시물 가져와서 저장한다.
		List<ElectronicsDTO> list = boardService.selectAll();
		model.addAttribute("list", list);
	}
	
	/**
	 * 등록폼
	 * */
	@RequestMapping("/write")
	public void write(/*HttpSession session */) {}
	
	/**
	 * 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(/*HttpSession session ,*/ElectronicsDTO electronics ) throws IOException{
		
		//파일이 첨부되었다면 폴더에저장, fname,fsize를 설정한다. 
		MultipartFile  mfile = electronics.getFile();
		if(mfile.getSize() > 0) {//첨부된파일에 용량이 있다면 
			mfile.transferTo(new File(PATH_SAVE+"/" + mfile.getOriginalFilename()));//폴더에 저장
			
			electronics.setFname(mfile.getOriginalFilename());
			electronics.setFsize(mfile.getSize());
		}
		
		boardService.insert(electronics);
		
		return "redirect:/board/list";
	}
	
	/**
	 * 상세보기
	 * */
	@RequestMapping("/read/{modelNum}")
	public ModelAndView read(/*HttpSession session ,*/@PathVariable String modelNum) {
		ElectronicsDTO dto = boardService.selectByModelNum(modelNum, true);//true 는 조회수 증가!!
		return new ModelAndView("board/read", "elec", dto);
	}
	
	/**
	 * 다운로드
	 * */
	@RequestMapping("/down")
	public ModelAndView down(/*HttpSession session ,*/String fname) {
		File file = new File(PATH_SAVE+"/" + fname);
		
		return new ModelAndView("downLoadView", "fname", file); //downLoadView는 bean의 id를 찾는다.
	}
	
	/**
	 * 수정폼
	 * */
	@RequestMapping("/updateForm")
	public String updateForm(/*HttpSession session ,*/ String modelNum , Model model) {
		ElectronicsDTO dto = boardService.selectByModelNum(modelNum, false);//false는 조회수증가안됨
		model.addAttribute("elec", dto);
		return "board/update";
	}
	
	
	/**
	 * 수정완료하기
	 * */
	@RequestMapping("/update")
	public ModelAndView update(/*HttpSession session ,*/ElectronicsDTO electronics) {
		boardService.update(electronics);
		ElectronicsDTO dbElec = boardService.selectByModelNum(electronics.getModelNum(), false);
		
		return new ModelAndView("board/read", "elec", dbElec);
	}
	
	/**
	 * 삭제하기
	 * */
	@RequestMapping("/delete")
	public String delete(/*HttpSession session ,*/ String modelNum, String password) {
		boardService.delete(modelNum, password);
		
		return "redirect:/board/list";
	}

}










