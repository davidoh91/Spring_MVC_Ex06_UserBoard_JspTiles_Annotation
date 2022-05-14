package kosta.web.mvc.user.repository;

import org.apache.ibatis.annotations.Select;

import kosta.web.mvc.user.dto.UserDTO;

/**
 * xml대신에 interface기반의 Mapper 만들기 
 *   : 사용하려면 springBean설정에 Mapper를 등록해야한다. 
 * */
public interface UserMapper {

	@Select("select user_id ,pwd, name from member where user_id=#{userId} and pwd=#{pwd}")
	UserDTO loginCheck(UserDTO userDTO);
}
