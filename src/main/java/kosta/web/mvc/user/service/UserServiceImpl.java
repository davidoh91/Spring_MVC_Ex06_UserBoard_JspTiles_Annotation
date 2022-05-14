package kosta.web.mvc.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kosta.web.mvc.user.dto.UserDTO;
import kosta.web.mvc.user.repository.UserDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserDAO userDAO;
	

	@Override
	public UserDTO loginCheck(UserDTO userDTO) {
		UserDTO dbDTO = userDAO.loginCheck(userDTO);
		if(dbDTO==null)throw new RuntimeException("정보가 일치하지않습니다.");
		
		return dbDTO;
	}

}
