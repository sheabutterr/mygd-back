package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.dto.BoardDto;
import project.dto.ClassDto;
import project.mapper.ClassMapper;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassMapper classMapper;

	@Override
	public List<BoardDto> selectComList() throws Exception {
		return classMapper.selectComList();
	}

	@Override
	public int insertCom(BoardDto comDto) throws Exception {
		return classMapper.insertCom(comDto);

	}

	@Override
	public BoardDto selectComDetail(int comId) throws Exception {
		classMapper.updateComHitCount(comId);
		return classMapper.selectComDetail(comId);
	}

	@Override
	public int updateCom(BoardDto comDto) throws Exception {
		return classMapper.updateCom(comDto);

	}

	@Override
	public int deleteCom(int comId) throws Exception {
		return classMapper.deleteCom(comId);

	}
	
	@Override
	public List<BoardDto> selectCategory() throws Exception {
		return classMapper.selectCategory();
	}

	@Override
	public List<BoardDto> selectFiveComByComId(int comId) throws Exception {
		return classMapper.selectFiveComByComId(comId);
	}

	

	@Override
	public List<BoardDto> selectQnaList() throws Exception {
		return classMapper.selectQnaList();
	}

	@Override
	public int insertQna(BoardDto boardDto) throws Exception {
		return classMapper.insertQna(boardDto);
	}

	@Override
	public BoardDto selectQnaDetail(int qnaId) throws Exception {
		classMapper.updateQnaHitCount(qnaId);
		return classMapper.selectQnaDetail(qnaId);
	}

	@Override
	public int updateQna(BoardDto boardDto) throws Exception {
		return classMapper.updateQna(boardDto);
	}

	@Override
	public int deleteQna(int qnaId) throws Exception {
		return classMapper.deleteQna(qnaId);
	}

	@Override
	public List<ClassDto> selectClassList() throws Exception {
		return classMapper.selectClassList();
	}

	@Override
	public ClassDto selectClassByClassId(int classId) throws Exception {
		return classMapper.selectClassByClassId(classId);
	}

	@Override
	public int updateReply(BoardDto boardDto) throws Exception {
		return classMapper.updateReply(boardDto);
	}

	@Override
	public List<BoardDto> selectFiveComByComId() throws Exception {
		return classMapper.selectFiveComByComId();
	}

}
