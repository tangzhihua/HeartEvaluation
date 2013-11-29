package cn.skyduck.question_frame_fragment.aslec;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ASLECPicturePageDataSource implements Serializable {
	// 问题标题
	private String questionTitleString;

	public ASLECPicturePageDataSource(String questionTitleString) {
		this.questionTitleString = questionTitleString;
	}

	public String getQuestionTitleString() {
		return questionTitleString;
	}


}
