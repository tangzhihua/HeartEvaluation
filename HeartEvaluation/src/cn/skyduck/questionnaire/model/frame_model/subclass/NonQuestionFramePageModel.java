package cn.skyduck.questionnaire.model.frame_model.subclass;

import java.io.Serializable;

import android.support.v4.app.Fragment;
import cn.skyduck.question_frame_fragment.NonQuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;

/**
 * 非问题界面 模型类 (包括 问卷者信息录入界面, 指导语界面, 答题过程中的过渡界面)
 * 
 * @author skyduck
 * 
 */
@SuppressWarnings("serial")
public class NonQuestionFramePageModel extends BaseFramePageModel implements Serializable{

	public NonQuestionFramePageModel(IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory) {
		super(questionFragmentFactory);
	}

	@Override
	public Fragment createQuestionnaireFramePageFragment(Object dataSource) {
		if (dataSource == null || !(dataSource instanceof FullQuestionnaireModel)) {
			assert false : "入参数据类型错误, 应该是FullQuestionnaireModel";
			return null;
		}

		NonQuestionFragmentDataSource nonQuestionFragmentDataSource = new NonQuestionFragmentDataSource(this.pageDataSource, this.userAnswerDataSource, this.getFragmentStyle());
		return questionFragmentFactory.createQuestionnaireFramePageFragment(nonQuestionFragmentDataSource);
	}

}
