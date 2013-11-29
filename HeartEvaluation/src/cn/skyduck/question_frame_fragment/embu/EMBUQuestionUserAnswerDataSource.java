package cn.skyduck.question_frame_fragment.embu;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EMBUQuestionUserAnswerDataSource implements Serializable {
	// 父亲选项
	private int optionOfFather = -1;

	// 母亲选项
	private int optionOfMother = -1;

	public int getOptionOfFather() {
		return optionOfFather;
	}

	public void setOptionOfFather(int optionOfFather) {
		this.optionOfFather = optionOfFather;
	}

	public int getOptionOfMother() {
		return optionOfMother;
	}

	public void setOptionOfMother(int optionOfMother) {
		this.optionOfMother = optionOfMother;
	}

}
