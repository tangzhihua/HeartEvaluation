package cn.skyduck.question_frame_fragment.bssd;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class BSSDPicturePageDataSource implements Serializable {
	// 问题标题
	private String questionTitleString;
	// 问题圖片
	private int questionPictureResId;
	// 该问题对应的答案列表
	private List<String> anwserList;

	public BSSDPicturePageDataSource(String questionTitleString, int questionPictureResId, List<String> anwserList) {
		this.questionTitleString = questionTitleString;
		this.questionPictureResId = questionPictureResId;
		this.anwserList = anwserList;
	}

	public String getQuestionTitleString() {
		return questionTitleString;
	}

	public int getQuestionPictureResId() {
		return questionPictureResId;
	}

	public List<String> getAnwserList() {
		return anwserList;
	}

}
