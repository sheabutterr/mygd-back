package project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.dto.BoardDto;
import project.dto.ClassDto;

@Mapper
public interface ClassMapper {
	List<BoardDto> selectFiveComByComId(int comId) throws Exception;
	
	List<BoardDto> selectComList() throws Exception;
	int insertCom(BoardDto comDto) throws Exception;
	void updateComHitCount(int comId) throws Exception;
	BoardDto selectComDetail(int comId) throws Exception;	
	int updateCom(BoardDto comDto) throws Exception;
	int deleteCom(int comId) throws Exception;
	List<BoardDto> selectCategory() throws Exception;
	
	List<BoardDto> selectQnaList() throws Exception;
    int insertQna(BoardDto boardDto) throws Exception;
    void updateQnaHitCount(int qnaId) throws Exception;
    BoardDto selectQnaDetail(int qnaId) throws Exception;
    int updateQna(BoardDto boardDto) throws Exception;
    int deleteQna(int qnaId) throws Exception;
    int updateReply(BoardDto boardDto) throws Exception;
	
    List<ClassDto> selectClassList() throws Exception;
    ClassDto selectClassByClassId(int classId) throws Exception;
    
    List<BoardDto> selectFiveComByComId() throws Exception;

}
