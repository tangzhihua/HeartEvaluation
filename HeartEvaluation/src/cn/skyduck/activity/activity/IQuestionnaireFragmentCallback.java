package cn.skyduck.activity.activity;

/**
 * 问卷Fragment 和 问卷Activity 通信接口
 * 
 * @author skyduck
 * 
 */
public interface IQuestionnaireFragmentCallback {
	// 上一页
	public void onPreviousPage();

	// 下一页
	public void onNextPage();

	// 结束测试
	public void onEndTest();

	// 用户回答了问题
	public void onAnswerQuestion(final Object userAnswer);
}
