package project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.dto.BoardDto;
import project.dto.ClassDto;
import project.security.JwtTokenUtil;
import project.service.ClassService;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClassController {

	@Autowired
	private ClassService classService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;	
	
	// 메인 화면에 최신글 출력
		@GetMapping("/latestList")
		public ResponseEntity<List<BoardDto>> openMain() throws Exception {
			List<BoardDto> LatestList = classService.selectFiveComByComId();
			if (LatestList != null && LatestList.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(LatestList);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		}

	// 커뮤니티 글 조회
	@GetMapping("/community")
	public ResponseEntity<List<BoardDto>> openComList() throws Exception {
		List<BoardDto> list = classService.selectComList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}	

	// 커뮤니티 글 작성
	@PostMapping("/community/write")
	public ResponseEntity<Map<String, Object>> insertCom(@RequestBody BoardDto comDto, HttpServletRequest request)
			throws Exception {

		String jwtToken = null;
		String subject = null;
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken = authorizationHeader.substring(7);
			subject = jwtTokenUtil.getSubjectFromToken(jwtToken);
		}
		comDto.setComCreatedId(subject);

		int insertedCount = 0;
		try {
			insertedCount = classService.insertCom(comDto);
			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("comId", comDto.getComId());
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			result.put("message", "등록 중 오류가 발생했습니다.");
			result.put("count", insertedCount);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	// 커뮤니티 글 상세 조회
	@GetMapping("/community/{comId}")
	public ResponseEntity<BoardDto> openComDetail(@PathVariable("comId") int comId) throws Exception {
		BoardDto comDto = classService.selectComDetail(comId);
		if (comDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(comDto);
		}
	}

	// 커뮤니티 글 수정
	@PutMapping("/community/{comId}")
	public ResponseEntity<Integer> updateCom(@PathVariable("comId") int comId, @RequestBody BoardDto comDto)
			throws Exception {
		comDto.setComId(comId);
		int updatedCount = classService.updateCom(comDto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
	}

	// 커뮤니티 글 삭제
	@DeleteMapping("/community/{comId}")
	public ResponseEntity<Integer> deleteCom(@PathVariable("comId") int comId) throws Exception {
		int deletedCount = classService.deleteCom(comId);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}

	// 카테고리 선택
	@GetMapping("/category")
	public ResponseEntity<List<BoardDto>> openCategory() throws Exception {
		List<BoardDto> list = classService.selectCategory();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// qna 목록 조회
	@GetMapping("/qna")
	public ResponseEntity<List<BoardDto>> openBoardList() throws Exception {
		List<BoardDto> list = classService.selectQnaList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// qna 작성
	@PostMapping("/qna/write")
	public ResponseEntity<Map<String, Object>> insertQna(@RequestBody BoardDto boardDto, HttpServletRequest request)
			throws Exception {
		String jwtToken = null;
		String subject = null;
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken = authorizationHeader.substring(7);
			subject = jwtTokenUtil.getSubjectFromToken(jwtToken);
		}
		boardDto.setQnaCreatedId(subject);
		log.debug(jwtToken);
		int insertedCount = 0;
		try {
			insertedCount = classService.insertQna(boardDto);
			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("qnaId", boardDto.getQnaId());
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			result.put("message", "등록 중 오류가 발생했습니다.");
			result.put("count", insertedCount);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	// qna 상세 조회
	@GetMapping("/qna/{qnaId}")
	public ResponseEntity<BoardDto> openQnaDetail(@PathVariable("qnaId") int qnaId) throws Exception {
		BoardDto boardDto = classService.selectQnaDetail(qnaId);
		if (boardDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(boardDto);
		}
	}

	// qna 수정
	@PutMapping("/qna/{qnaId}")
	public ResponseEntity<Integer> updateQna(@PathVariable("qnaId") int qnaId, @RequestBody BoardDto boardDto)
			throws Exception {
		boardDto.setQnaId(qnaId);
		int updatedCount = classService.updateQna(boardDto);
		if (updatedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updatedCount);
		}
	}

	// qna 삭제
	@DeleteMapping("/qna/{qnaId}")
	public ResponseEntity<Integer> deleteQna(@PathVariable("qnaId") int qnaId) throws Exception {
		int deletedCount = classService.deleteQna(qnaId);
		if (deletedCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(deletedCount);
		}
	}

	// 댓글 작성
	@PutMapping("/reply/{qnaId}")
	public ResponseEntity<Integer> updateReply(@PathVariable("qnaId") int qnaId, @RequestBody BoardDto boardDto)
			throws Exception {
		boardDto.setQnaId(qnaId);
		int updateReply = classService.updateReply(boardDto);
		if (updateReply != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateReply);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(updateReply);
		}
	}

	// 클래스 목록
	@GetMapping("/classList")
	public ResponseEntity<List<ClassDto>> classList() throws Exception {
		List<ClassDto> classDto = classService.selectClassList();
		if (classDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(classDto);
		}
	}

	// 클래스 상세페이지
	@GetMapping("/classList/{classId}")
	public ResponseEntity<ClassDto> classDetail(@PathVariable("classId") int classId) throws Exception {
		ClassDto classDto = classService.selectClassByClassId(classId);
		if (classDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(classDto);
		}

	}

	// 수강신청
	@GetMapping("/apply")
	public ResponseEntity<List<ClassDto>> applyList() throws Exception {
		List<ClassDto> list = classService.selectClassList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
