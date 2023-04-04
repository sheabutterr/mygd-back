package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.dto.UserDto;
import project.service.UserService;

@Slf4j
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	// 회원 가입
	@PostMapping("/regist")
	public ResponseEntity<Object> regist(@RequestBody UserDto userDto) throws Exception {		
		
		int registedCount = userService.registUser(userDto);
		if (registedCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(registedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(registedCount);
		}
	}
	

	// 마이페이지 조회
	@GetMapping("/myInfo/{userIdx}")
	public ResponseEntity<UserDto> myInfo(@PathVariable("userIdx") int userIdx) throws Exception {
		log.debug(">>>>>>>>>" + userIdx);
		UserDto userDto = userService.selectUserByUserIdx(userIdx);
		if (userDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(userDto);
		}
	}
	
	//마이페이지 수정
	@PutMapping("/myInfo/{userIdx}")
	public ResponseEntity<Integer> updateInfo(@PathVariable("userIdx") int userIdx, @RequestBody UserDto userDto) throws Exception {
		log.debug(">>>>>>>>>" + userIdx);
		userDto.setUserIdx(userIdx);
		int updateUser = userService.updateUser(userDto);
		if (updateUser != 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateUser);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		}
	}
	

	// 레벨 테이블 조회
	@GetMapping("/level")
	public ResponseEntity<List<UserDto>> selectLevel() throws Exception {
		List<UserDto> list = userService.selectLevel();		
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// 레벨 테스트 등록
	@PutMapping("/level/{userIdx}")
	public ResponseEntity<Integer> registLevel(@PathVariable("userIdx") int userIdx, @RequestBody UserDto userDto) throws Exception {
		userDto.setUserIdx(userIdx);
		int registLevel = userService.registLevel(userDto);
		if (registLevel != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registLevel);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(registLevel);
		}		
	}
	
	// 수강 신청 등록
	@PutMapping("/apply/{userIdx}")
	public ResponseEntity<Integer> registClass(@PathVariable("userIdx") int userIdx, @RequestBody UserDto userDto) throws Exception {
		userDto.setUserIdx(userIdx);
		int registClass = userService.registClass(userDto);
		if (registClass != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registClass);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(registClass);
		}		
	}
	
}
