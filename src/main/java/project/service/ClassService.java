package project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.dto.BoardDto;
import project.dto.ClassDto;

@Service
public interface ClassService {
	public List<BoardDto> selectFiveComByComId(int comId) throws Exception;
	
	public List<BoardDto> selectComList() throws Exception;
	public int insertCom(BoardDto comDto) throws Exception;	
	public BoardDto selectComDetail(int comId) throws Exception;	
	public int updateCom(BoardDto comDto) throws Exception;
	public int deleteCom(int comId) throws Exception;
	public List<BoardDto> selectCategory() throws Exception;
	
	public List<BoardDto> selectQnaList() throws Exception;
	public int insertQna(BoardDto boardDto) throws Exception;	
	public BoardDto selectQnaDetail(int qnaId) throws Exception;
	public int updateQna(BoardDto boardDto) throws Exception;
	public int deleteQna(int qnaId) throws Exception;
	public int updateReply(BoardDto boardDto) throws Exception;
	
	public List<ClassDto> selectClassList() throws Exception;
    public ClassDto selectClassByClassId(int classId) throws Exception;

    public List<BoardDto> selectFiveComByComId() throws Exception;

}
