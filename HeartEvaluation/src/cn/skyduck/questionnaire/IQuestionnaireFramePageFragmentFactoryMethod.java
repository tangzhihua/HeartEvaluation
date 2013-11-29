package cn.skyduck.questionnaire;

import android.support.v4.app.Fragment;

public interface IQuestionnaireFramePageFragmentFactoryMethod {
	public static final String kDataSourceKey = "kDataSourceKey";

	/**
	 * 创建一个 "答题界面 - 碎片" 的工厂方法接口
	 * 
	 * 答题界面目前包括3部分 : 1.受访者个人信息录入界面; 2.答题指导语界面; 3.问卷界面 这三部分的 碎片界面 都要使用这个接口来生成
	 * 
	 * @param dataSource
	 *          数据源
	 * 
	 * @return
	 */
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource);
}
