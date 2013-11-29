package cn.skyduck.question_frame_fragment.single_answer_expansion_1;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
/**
 * 单选题 - 扩展 1
 * 
 * 为量表 : GWB/SAI 量表准备
 * @author skyduck
 *
 */
public class SingleAnswerExpansionOnePageDataSource implements Serializable {
	// 问题标题
	private String questionTitle;
	// 该问题对应的答案列表
	private List<String> anwserList;
	// 答题提示(左)
	private String promptLeft;
	// 答案提示(右)
	private String promptRight;

	public SingleAnswerExpansionOnePageDataSource(String questionTitle, List<String> anwserList, String promptLeft, String promptRight) {
		this.questionTitle = questionTitle;
		this.anwserList = anwserList;
		this.promptLeft = promptLeft;
		this.promptRight = promptRight;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public List<String> getAnwserList() {
		return anwserList;
	}

	public String getPromptLeft() {
		return promptLeft;
	}

	public String getPromptRight() {
		return promptRight;
	}

}
