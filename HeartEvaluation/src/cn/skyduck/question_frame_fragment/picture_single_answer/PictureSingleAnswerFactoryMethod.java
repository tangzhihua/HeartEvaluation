package cn.skyduck.question_frame_fragment.picture_single_answer;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;

@SuppressWarnings("serial")
public class PictureSingleAnswerFactoryMethod implements IQuestionnaireFramePageFragmentFactoryMethod, Serializable {

	@Override
	public Fragment createQuestionnaireFramePageFragment(Object dataSource) {
		if (dataSource == null || !(dataSource instanceof QuestionFragmentDataSource)) {
			assert false : "入参数据类型错误, 应该是NonQuestionFragmentDataSource";
			return null;
		}
		final QuestionFragmentDataSource questionFragmentDataSource = (QuestionFragmentDataSource) dataSource;

		// 将包含问题碎片的对象进行序列化并传入到属性中。
		Bundle arguments = new Bundle();
		arguments.putSerializable(IQuestionnaireFramePageFragmentFactoryMethod.kDataSourceKey, (Serializable) questionFragmentDataSource);

		// 创建碎片 并为碎片设置属性，这样在碎片中就可以取出出相应的数据，最终得到包含每一道题的界面
		PictureSingleAnswerFragment fragment = new PictureSingleAnswerFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

}
