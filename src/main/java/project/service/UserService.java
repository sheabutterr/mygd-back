package project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import project.dto.UserDto;


@Service
public interface UserService extends UserDetailsService {
	
	public UserDto login(UserDto userDto) throws Exception;	
	public int registUser(UserDto userDto) throws Exception;
	public UserDto selectUserByUserId(String userId);
	
	public List<UserDto> selectLevel() throws Exception;
	public int registLevel(UserDto userDto) throws Exception;
	
	public UserDto selectUserByUserIdx(int userIdx);
	public int updateUser(UserDto userDto) throws Exception;
	
	public int registClass(UserDto userDto) throws Exception;
	

}
