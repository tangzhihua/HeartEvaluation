package cn.skyduck.questionnaire.questionnaire.GDS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfGDS extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1．你对生活基本上满意吗?",
	 "2．你是否已放弃了许多活动与兴趣?",
	 "3．你是否觉得生活空虚?",
	 "4．你是否常感到厌倦?",
	 "5，你觉得未来有希望吗?",
	 "6．你是否因为脑子里一些想法摆脱不掉而烦恼?",
	 "7．你是否大部分时间精力充沛?",
	 "8．你是否害怕会有不幸的事落到你头上?",
	 "9．你是否大部分时间感到幸福?",
	 "10．你是否常感到孤立无援?",
	 "11．你是否经常坐立不安，心烦意乱?",
	 "12．你是否希望呆在家里而不愿去做些新鲜事?",
	 "13．你是否常常担心将来?",
	 "14．你是否觉得记忆力比以前差?",
	 "15．你觉得现在活着很惬意吗?",
	 "16．你是否常感到心情沉重、郁闷?",
	 "17．你是否觉得象现在这样活着毫无意义?",
	 "18．你是否总为过去的事忧愁?",
	 "19．你觉得生活很令人兴奋吗?",
	 "20．你开始一件新的工作很困难吗?",
	 "21．你觉得生活充满活力吗?",
	 "22．你是否觉得你的处境已毫无希望?",
	 "23．你是否觉得大多数人比你强得多?",
	 "24．你是否常为些小事伤心?",
	 "25．你是否常觉得想哭?",
	 "26．你集中精力有困难吗?",
	 "27．你早晨起来很快活吗?",
	 "28．你希望避开聚会吗?",
	 "29．你做决定很容易吗?",
	 "30．你的头脑象往常一样清晰吗?"
 };
 

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:选择最切合您最近一周来的感受的答案。" };

	public QuestionnaireModelOfGDS() {
		super(QuestionnaireCodeEnum.GDS);

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		framePageFragmentFactory = new FillTesterPersonalInformationOfAdultsFragmentFactoryMethod();
		final NonQuestionFramePageModel fillTesterInfoFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		fillTesterInfoFramePageModel.setAnswerDataSource(answerDataSource);
		super.frameModelList.add(fillTesterInfoFramePageModel);

		// 指导语界面
		final List<String> guidanceLanguageStrings = Arrays.asList(kGuidanceLanguage);
		framePageFragmentFactory = new GuidanceLanguageFragmentFactoryMethod();
		final NonQuestionFramePageModel guidanceLanguageFramePageModel = new NonQuestionFramePageModel(framePageFragmentFactory);
		guidanceLanguageFramePageModel.setQuestionDataSource(guidanceLanguageStrings);
		super.frameModelList.add(guidanceLanguageFramePageModel);

		int questionIndex = 0;
		framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], null);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			super.frameModelList.add(questionFrameModel);
		}

	}

	/**
	 * 检测当前问题是否能够被忽略(能被忽略的问题有很多种情况 : 1.此问题已经回答过; 2.当前帧不是问题帧; 3.已经回答的题目数量已经达到一定数量)
	 * 
	 * @return
	 */
	public boolean isCanBeIgnoredCurrentQuestion() {
		return true;
	}

	/**
	 * 检测本次测试是否有效
	 * 
	 * @return
	 */
	public boolean isTestEffectively() {
		do {
			if (super.getUnansweredQuestionNumberList().size() > 30) {
				// 注意当未答的题目大于等于30个时，不能让受测者点确定，因为那样就白答了
				break;
			}
			return true;
		} while (false);
		return false;
	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, 2);
	}

}
