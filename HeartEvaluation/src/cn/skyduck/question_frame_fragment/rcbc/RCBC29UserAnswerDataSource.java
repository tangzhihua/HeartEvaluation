package cn.skyduck.question_frame_fragment.rcbc;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RCBC29UserAnswerDataSource implements Serializable {

	public RCBC29UserAnswerDataSource() {
		resetPartTwoAnswer();
	}

	// 第一部分 单选 选项 答案
	private int optionPartOneRadioAnswer = -1;
	// 第二部分 复选框 答案
	private int[] optionPartTwoCheckBoxAnswerArray = new int[3];

	public int getOptionPartOneRadioAnswer() {
		return optionPartOneRadioAnswer;
	}

	public void setOptionPartOneRadioAnswer(int optionPartOneRadioAnswer) {
		this.optionPartOneRadioAnswer = optionPartOneRadioAnswer;
	}

	public int[] getOptionPartTwoCheckBoxAnswerArray() {
		return optionPartTwoCheckBoxAnswerArray;
	}

	/**
	 * 重置第二部分的答案
	 */
	public void resetPartTwoAnswer() {
		for (int i = 0; i < optionPartTwoCheckBoxAnswerArray.length; i++) {
			optionPartTwoCheckBoxAnswerArray[i] = -1;
		}
	}

}
