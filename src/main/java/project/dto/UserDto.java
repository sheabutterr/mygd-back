package project.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userIdx;
	private String userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	
	private int levelId;
	private String levelName;
	
	private int classId;
	private String className;

}
