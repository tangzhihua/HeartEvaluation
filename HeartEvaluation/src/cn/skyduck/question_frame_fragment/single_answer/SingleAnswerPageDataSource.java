package cn.skyduck.question_frame_fragment.single_answer;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SingleAnswerPageDataSource implements Serializable {
	// 问题标题
	private String questionTitle;
	// 该问题对应的答案列表
	private List<String> anwserList;
	// 答题提示
	private String prompt;

	public SingleAnswerPageDataSource(String questionTitle, List<String> anwserList) {
		this.questionTitle = questionTitle;
		this.anwserList = anwserList;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public List<String> getAnwserList() {
		return anwserList;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

}
