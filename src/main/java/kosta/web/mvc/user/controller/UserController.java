package kosta.web.mvc.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.user.dto.UserDTO;
import kosta.web.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	/**
	 * 로그인 폼
	 * */
	@RequestMapping("/login")
	public void login() {}
	
	/**
	 * 로그인하기
	 * */
	@RequestMapping("/loginCheck")
	public String loginCheck(UserDTO userDTO ,HttpSession session) {
		//서비스호출 하고 성공하면 리턴한 UserDTO를 받아서  
		UserDTO dbDTO = userService.loginCheck(userDTO);
		
		//HttpSession에 정보를 저장한다. - 뷰에서 사용하고 있음 ${loginUser} / ${loginName}
		session.setAttribute("loginUser", dbDTO.getUserId());
		session.setAttribute("loginName", dbDTO.getName());
		
		return "redirect:/";
	}
	
	/**
	 * 로그아웃
	 * */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//모든 세션의 정보를 삭제한다.
		session.invalidate();
		
		return "redirect:/";
	}
	
}










