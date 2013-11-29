package cn.skyduck.question_frame_fragment.fill_tester_personal_information.children;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.NonQuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class FillTesterPersonalInformationFragmentOfChildrenFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	@Override
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource) {
		if (dataSource == null || !(dataSource instanceof NonQuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}
		final NonQuestionFragmentDataSource nonQuestionFragmentDataSource = (NonQuestionFragmentDataSource) dataSource;

		Bundle arguments = new Bundle();
		arguments.putSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, (Serializable) nonQuestionFragmentDataSource.getAnswerDataSource());

		FillTesterPersonalInformationFragmentOfChildren fragment = new FillTesterPersonalInformationFragmentOfChildren();
		fragment.setArguments(arguments);
		return fragment;
	}

}
