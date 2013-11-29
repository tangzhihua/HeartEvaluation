package cn.skyduck.questionnaire.model.frame_model.subclass;

import java.io.Serializable;

import android.support.v4.app.Fragment;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.question_frame_fragment.QuestionFragmentDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;

/**
 * 问题界面的 模型类 (包括所有问卷答题界面)
 * 
 * @author skyduck
 * 
 */
@SuppressWarnings("serial")
public class QuestionFramePageModel extends BaseFramePageModel implements Serializable {

	// 是否需要区分患者性别, 好提供不同的数据源
	private boolean isNeedToDistinguishBetweenMaleAndFemale = false;
	// 男性患者 专有问题
	private Object questionDataSourceOfMale;
	// 女性患者 专有问题
	private Object questionDataSourceOfFemale;

	public QuestionFramePageModel(IQuestionnaireFramePageFragmentFactoryMethod questionFragmentFactory) {
		super(questionFragmentFactory);
	}

	/**
	 * 设置区分男女患者的问题数据源, 区分男女
	 * 
	 * @param questionDataSourceOfMale
	 * @param questionDataSourceOfFemale
	 */
	public void setQuestionDataSourceWithMaleAndFemale(final Object questionDataSourceOfMale, final Object questionDataSourceOfFemale) {
		this.isNeedToDistinguishBetweenMaleAndFemale = true;
		this.questionDataSourceOfMale = questionDataSourceOfMale;
		this.questionDataSourceOfFemale = questionDataSourceOfFemale;
	}

	/**
	 * 获取当前问题的问题标题(有可能区分男女患者)
	 * 
	 * @param isMalePatients
	 *          男性患者还是女性患者, 因为不同性别对应的问题不同
	 * @return
	 */
	public Object getQuestionDataSource(final GlobalConstant.SexEnum sexEnum) {
		Object dataSource = null;
		do {

			// 不需要区分男女患者
			if (!isNeedToDistinguishBetweenMaleAndFemale) {
				dataSource = pageDataSource;
				break;
			}

			// 需要区分男女患者
			if (sexEnum == GlobalConstant.SexEnum.MAN) {
				dataSource = questionDataSourceOfMale;// 男患者
			} else {
				dataSource = questionDataSourceOfFemale;// 女患者
			}
		} while (false);

		return dataSource;
	}

	@Override
	public Fragment createQuestionnaireFramePageFragment(final Object dataSource) {
		if (dataSource == null || !(dataSource instanceof FullQuestionnaireModel)) {
			assert false : "入参数据类型错误, 应该是FullQuestionnaireModel";
			return null;
		}

		final FullQuestionnaireModel fullQuestionnaireModel = (FullQuestionnaireModel) dataSource;
		final QuestionFragmentDataSource questionFragmentDataSource = 
				new QuestionFragmentDataSource(fullQuestionnaireModel, 
						fullQuestionnaireModel.getQuestionTotal(), 
						this.getQuestionIndex(),
						this.getQuestionDataSource(fullQuestionnaireModel.getRespondentsInformation().getSexEnum()), 
				    this.getAnswerDataSource(), 
				    this.getFragmentStyle(), 
				    fullQuestionnaireModel.isCanBeIgnoredCurrentQuestion());
		
		return questionFragmentFactory.createQuestionnaireFramePageFragment(questionFragmentDataSource);
	}

}
