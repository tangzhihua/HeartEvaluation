package cn.skyduck.questionnaire.model.frame_model;

import java.io.Serializable;

import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

/**
 * 一个完整的量表就是由这样的FramePageModel组成的
 * 
 * 每一帧碎片页面对应的模型(这个模型在AnswerActivity中被使用, 在AnswerActivity中会调用当前模型的
 * createQuestionnaireFramePageFragment 接口, 来创建具体的碎片界面)
 * 
 * @author skyduck
 * 
 */
@SuppressWarnings("serial")
public abstract class BaseFramePageModel implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	// 有的帧不是问题界面, 比如第0帧和第1帧, 还有就是 答题界面中 会有中间的提示界面, 所以我们会以判断 questionIndex 是否是
	// NonQuestionPage, 来判断当前帧是否是问题帧
	public static final int NonQuestionPage = -2012;
	private int questionIndex = NonQuestionPage;

	// 创建 当前帧碎片界面的 工厂方法
	protected IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory;
	// 页面上UI 数据源 (比如问题标题, 该问题所对应的答案)
	protected Object pageDataSource;
	// 用户所作出的回答 数据源 (用户所作出的回答, 要保存在这里, 比如单选题的话, 这里存在的就是单选题的选项索引,
	// 如果是一个界面中有多个问题需要用户回答, 这里就是一个自定义的 UserAnswerDataSource
	protected Object userAnswerDataSource;
	// 碎片界面风格(比如有的单选按钮水平显示, 有的垂直显示)
	protected Object fragmentStyle;

	public BaseFramePageModel(IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory) {
		this.questionFragmentFactory = questionFragmentFactory;
	}

	// 问题编号索引, 从 0 开始
	public int getQuestionIndex() {
		return questionIndex;
	}

	/**
	 * 设置当前问题题号, 注意索引从 0 开始
	 * 
	 * @return
	 */
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	/**
	 * 获取当前问题索引, 注意索引从 0 开始
	 * 
	 * @return
	 */
	public Object getQuestionDataSource() {
		return pageDataSource;
	}

	public void setQuestionDataSource(Object questionDataSource) {
		this.pageDataSource = questionDataSource;
	}

	public Object getAnswerDataSource() {
		return userAnswerDataSource;
	}

	public void setAnswerDataSource(Object answerDataSource) {
		this.userAnswerDataSource = answerDataSource;
	}

	public Object getFragmentStyle() {
		return fragmentStyle;
	}

	public void setFragmentStyle(Object fragmentStyle) {
		this.fragmentStyle = fragmentStyle;
	}

}
