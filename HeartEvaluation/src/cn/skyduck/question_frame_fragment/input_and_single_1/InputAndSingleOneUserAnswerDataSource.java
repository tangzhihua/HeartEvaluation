package cn.skyduck.question_frame_fragment.input_and_single_1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InputAndSingleOneUserAnswerDataSource implements Serializable {
	// 单选题答案选项
	private int singleAnswerOption = -1;
	// 输入框中的内容
	private String inputBoxContent;

	public int getSingleAnswerOption() {
		return singleAnswerOption;
	}

	public void setSingleAnswerOption(int singleAnswerOption) {
		this.singleAnswerOption = singleAnswerOption;
	}

	public String getInputBoxContent() {
		return inputBoxContent;
	}

	public void setInputBoxContent(String inputBoxContent) {
		this.inputBoxContent = inputBoxContent;
	}

}
