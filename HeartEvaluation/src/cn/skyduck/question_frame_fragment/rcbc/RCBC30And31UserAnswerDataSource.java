package cn.skyduck.question_frame_fragment.rcbc;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RCBC30And31UserAnswerDataSource implements Serializable {

	public RCBC30And31UserAnswerDataSource() {
		resetPartTwoAnswer();
	}

	// 第一部分 单选 选项 答案
	private int optionPartOneRadioAnswer = -1;
	// 第二部分 单选 选项 答案
	private int optionPartTwoRadioAnswer = -1;
	// 输入框
	private String optionPartTwoInputAnswer;

	public int getOptionPartOneRadioAnswer() {
		return optionPartOneRadioAnswer;
	}

	public void setOptionPartOneRadioAnswer(int optionPartOneRadioAnswer) {
		this.optionPartOneRadioAnswer = optionPartOneRadioAnswer;
	}

	public int getOptionPartTwoRadioAnswer() {
		return optionPartTwoRadioAnswer;
	}

	public void setOptionPartTwoRadioAnswer(int optionPartTwoRadioAnswer) {
		this.optionPartTwoRadioAnswer = optionPartTwoRadioAnswer;
	}

	public String getOptionPartTwoInputAnswer() {
		return optionPartTwoInputAnswer;
	}

	public void setOptionPartTwoInputAnswer(String optionPartTwoInputAnswer) {
		this.optionPartTwoInputAnswer = optionPartTwoInputAnswer;
	}

	/**
	 * 重置第二部分的答案
	 */
	public void resetPartTwoAnswer() {
		optionPartTwoRadioAnswer = -1;
		optionPartTwoInputAnswer = "";
	}

}
