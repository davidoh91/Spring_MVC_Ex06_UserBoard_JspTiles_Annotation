package kosta.web.mvc.user.service;

import kosta.web.mvc.user.dto.UserDTO;

public interface UserService {
	/**
	 * 로그인 체크
	 * */
   UserDTO loginCheck(UserDTO userDTO);
}
