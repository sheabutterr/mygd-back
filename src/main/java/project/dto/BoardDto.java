package project.dto;

import lombok.Data;

@Data
public class BoardDto {
	private int comId;
	private String comTitle;
	private String comContents;
	private int comHitCnt;
	private String comCreatedId;
	private String comCreatedDt;
	private String comUpdatedDt;
	private String comDeletedYn;
	private int recommend;
	
	private int qnaId;
	private String qnaTitle;
	private String qnaContents;
	private int qnaHitCnt;
	private String qnaCreatedDt;
	private String qnaUpdatedDt;
	private String qnaDeletedYn;	
	private String qnaCreatedId;
	private String reply;
	
	private String userId;
	private int userIdx;
	
	private int categoryId;
	private String categoryName;

}
