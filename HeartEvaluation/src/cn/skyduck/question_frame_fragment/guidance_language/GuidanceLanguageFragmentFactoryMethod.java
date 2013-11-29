package cn.skyduck.question_frame_fragment.guidance_language;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.NonQuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class GuidanceLanguageFragmentFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable{

	@SuppressWarnings("unchecked")
	@Override
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource) {

		if (dataSource == null || !(dataSource instanceof NonQuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}

		final NonQuestionFragmentDataSource nonQuestionFragmentDataSource = (NonQuestionFragmentDataSource) dataSource;

		Bundle arguments = new Bundle();
		final List<String> listStrings = (List<String>) nonQuestionFragmentDataSource.getQuestionDataSource();
		arguments.putStringArrayList(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, new ArrayList<String>(listStrings));

		GuidanceLanguageFragment fragment = new GuidanceLanguageFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

}
