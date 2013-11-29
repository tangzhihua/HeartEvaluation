package cn.skyduck.question_frame_fragment.transition_page_1;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.NonQuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class TransitionPageOneFragmentFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	@Override
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource) {
		if (dataSource == null || !(dataSource instanceof NonQuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}

		final NonQuestionFragmentDataSource nonQuestionFragmentDataSource = (NonQuestionFragmentDataSource) dataSource;

		Bundle arguments = new Bundle();
		arguments.putString(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, (String) nonQuestionFragmentDataSource.getQuestionDataSource());

		TransitionPageOneFragment fragment = new TransitionPageOneFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

}
