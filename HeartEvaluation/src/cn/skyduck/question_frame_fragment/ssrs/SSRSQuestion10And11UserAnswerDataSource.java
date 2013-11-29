package cn.skyduck.question_frame_fragment.ssrs;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SSRSQuestion10And11UserAnswerDataSource implements Serializable {

	public SSRSQuestion10And11UserAnswerDataSource() {
		resetPartTwoAnswer();
	}

	// 第一部分 单选 选项 答案
	private int optionPartOneRadioAnswer = -1;
	// 第二部分 复选框 答案
	private boolean[] optionPartTwoCheckBoxAnswerArray = new boolean[8];
	// 第二部分 输入框 答案
	private String[] optionPartTwoInputAnswerArray = new String[5];

	public int getOptionPartOneRadioAnswer() {
		return optionPartOneRadioAnswer;
	}

	public void setOptionPartOneRadioAnswer(int optionPartOneRadioAnswer) {
		this.optionPartOneRadioAnswer = optionPartOneRadioAnswer;
	}

	public boolean[] getOptionPartTwoCheckBoxAnswerArray() {
		return optionPartTwoCheckBoxAnswerArray;
	}

	public String[] getOptionPartTwoInputAnswerArray() {
		return optionPartTwoInputAnswerArray;
	}

	/**
	 * 重置第二部分的答案
	 */
	public void resetPartTwoAnswer() {
		for (int i = 0; i < optionPartTwoCheckBoxAnswerArray.length; i++) {
			optionPartTwoCheckBoxAnswerArray[i] = false;
		}
		for (int i = 0; i < optionPartTwoInputAnswerArray.length; i++) {
			optionPartTwoInputAnswerArray[i] = "";
		}
	}

}
