package cn.skyduck.question_frame_fragment.hpii;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class HPIIPartOneFragmentFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	public static enum FragmentStyleEnum {
		// 垂直
		vertical,
		// 水平
		horizontal,
		// 判断题
		true_or_false;
	}

	@Override
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource) {
		if (dataSource == null || !(dataSource instanceof QuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}
		final QuestionFragmentDataSource questionFragmentDataSource = (QuestionFragmentDataSource) dataSource;

		Bundle arguments = new Bundle();
		arguments.putSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, (Serializable) questionFragmentDataSource);

		HPIIPartOneFragment fragment = new HPIIPartOneFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

}
