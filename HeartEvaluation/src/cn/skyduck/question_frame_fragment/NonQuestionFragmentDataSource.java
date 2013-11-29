package cn.skyduck.question_frame_fragment;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NonQuestionFragmentDataSource implements Serializable {
	// 问题数据源
	private Object questionDataSource;
	// 用户答案数据源
	private Object answerDataSource;
	// 碎片风格
	private Object fragmentStyle;

	public NonQuestionFragmentDataSource(Object questionDataSource, Object answerDataSource, Object fragmentStyle) {
		this.questionDataSource = questionDataSource;
		this.answerDataSource = answerDataSource;
		this.fragmentStyle = fragmentStyle;
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

}
