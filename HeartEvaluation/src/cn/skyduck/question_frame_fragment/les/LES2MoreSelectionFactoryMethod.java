package cn.skyduck.question_frame_fragment.les;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class LES2MoreSelectionFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	@Override
	public Fragment createQuestionnaireFramePageFragment(Object dataSource) {
		if (dataSource == null || !(dataSource instanceof QuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}
		final QuestionFragmentDataSource questionFragmentDataSource = (QuestionFragmentDataSource) dataSource;

		Bundle arguments = new Bundle();
		arguments.putSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, (Serializable) questionFragmentDataSource);

		LES2MoreSelectionFragment fragment = new LES2MoreSelectionFragment();
		fragment.setArguments(arguments);
		return fragment;
	}
}
