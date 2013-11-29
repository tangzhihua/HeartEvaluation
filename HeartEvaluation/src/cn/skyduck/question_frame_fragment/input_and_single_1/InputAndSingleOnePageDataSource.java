package cn.skyduck.question_frame_fragment.input_and_single_1;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class InputAndSingleOnePageDataSource implements Serializable {
	// 问题标题
	private String questionTitle;
	// 该问题对应的答案列表
	private List<String> anwserList;
	// 输入框问题标题
	private String inputBoxTitle;

	public InputAndSingleOnePageDataSource(String questionTitle, List<String> anwserList, String inputBoxTitle) {
		this.questionTitle = questionTitle;
		this.anwserList = anwserList;
		this.inputBoxTitle = inputBoxTitle;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public List<String> getAnwserList() {
		return anwserList;
	}

	public String getInputBoxTitle() {
		return inputBoxTitle;
	}

}
