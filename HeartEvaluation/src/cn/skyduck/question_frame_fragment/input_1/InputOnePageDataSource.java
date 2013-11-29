package cn.skyduck.question_frame_fragment.input_1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InputOnePageDataSource implements Serializable {
	// 问题标题
	private String questionTitle;
	// 输入框问题标题
	private String inputBoxTitle;

	public InputOnePageDataSource(String questionTitle, String inputBoxTitle) {
		this.questionTitle = questionTitle;
		this.inputBoxTitle = inputBoxTitle;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public String getInputBoxTitle() {
		return inputBoxTitle;
	}

}
