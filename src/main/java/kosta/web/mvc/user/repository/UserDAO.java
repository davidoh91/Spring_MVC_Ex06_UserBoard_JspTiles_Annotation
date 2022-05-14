package kosta.web.mvc.user.repository;

import kosta.web.mvc.user.dto.UserDTO;

public interface UserDAO {
  /**
   * 로그인 기능
   * */
	UserDTO loginCheck(UserDTO userDTO);
}
