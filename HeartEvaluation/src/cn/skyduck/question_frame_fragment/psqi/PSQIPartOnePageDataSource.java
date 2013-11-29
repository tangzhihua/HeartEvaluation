package cn.skyduck.question_frame_fragment.psqi;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PSQIPartOnePageDataSource implements Serializable {
	// 问题标题
	private List<String> questionList;
	// 该问题对应的答案列表
	private String anwserString;

	public PSQIPartOnePageDataSource(List<String> questionList, String anwserString) {
		this.questionList = questionList;
		this.anwserString = anwserString;
	}

	public List<String> getQuestionList() {
		return questionList;
	}

	public String getAnwserString() {
		return anwserString;
	}

}
