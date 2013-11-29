package cn.skyduck.question_frame_fragment;

import java.io.Serializable;

import cn.skyduck.questionnaire.model.FullQuestionnaireModel;

@SuppressWarnings("serial")
/**
 * 传递给 问题碎片 界面的模型类
 * @author skyduck
 *
 */
public class QuestionFragmentDataSource implements Serializable {

	// 问题总数
	private int questionTotal;
	// 当前是第几题(题目索引从 0 开始)
	private int currentQuestionIndex;
	// 问题数据源
	private Object questionDataSource;
	// 用户答案数据源
	private Object answerDataSource;
	// 碎片风格
	private Object fragmentStyle;
	// 是否可以忽略当前问题
	private boolean isCanBeIgnoredThisQuestion;
	// 完整的量表模型
	private FullQuestionnaireModel fullQuestionnaireModel;

	public QuestionFragmentDataSource(FullQuestionnaireModel fullQuestionnaireModel, int questionTotal, int currentQuestionIndex, Object questionDataSource, Object answerDataSource,
			Object fragmentStyle, boolean isCanBeIgnoredThisQuestion) {
		this.fullQuestionnaireModel = fullQuestionnaireModel;
		this.questionTotal = questionTotal;
		this.currentQuestionIndex = currentQuestionIndex;
		this.questionDataSource = questionDataSource;
		this.answerDataSource = answerDataSource;
		this.fragmentStyle = fragmentStyle;
		this.isCanBeIgnoredThisQuestion = isCanBeIgnoredThisQuestion;
	}

	public int getQuestionTotal() {
		return questionTotal;
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}

	public Object getQuestionDataSource() {
		return questionDataSource;
	}

	public Object getAnswerDataSource() {
		return answerDataSource;
	}

	public Object getFragmentStyle() {
		return fragmentStyle;
	}

	public boolean isCanBeIgnoredThisQuestion() {
		return isCanBeIgnoredThisQuestion;
	}

	public FullQuestionnaireModel getFullQuestionnaireModel() {
		return fullQuestionnaireModel;
	}

}
