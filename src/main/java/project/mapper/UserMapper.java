package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.dto.UserDto;

@Mapper
public interface UserMapper {
	
	public UserDto login(UserDto userDto) throws Exception;	
	public int registUser(UserDto userDto) throws Exception;	
	public UserDto selectUserByUserId(String userId);
	public UserDto selectUserByUserIdx(int userIdx);
	public List<UserDto> selectLevel() throws Exception;
	public int registLevel(UserDto userDto) throws Exception;
	public int registClass(UserDto userDto) throws Exception;
	public int updateUser(UserDto userDto) throws Exception;

}
