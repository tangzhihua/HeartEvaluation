package cn.skyduck.question_frame_fragment.psqi;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class PSQIPartOneFragmentFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	public static enum FragmentStyleEnum {
		// 点击EditText之后弹出时间选择器
		clicked_open_time_selector,
		// 点击EditText之后弹出输入法软键盘
		clicked_open_soft_keyboard;
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

		PSQIPartOneFragment fragment = new PSQIPartOneFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

}
