package cn.skyduck.questionnaire.questionnaire.FS_14;

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
public class QuestionnaireModelOfFS_14 extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1．你有过被疲劳困扰的经历吗?",
   "2．你是否需要更多的休息?",
   "3．你感觉到犯困或昏昏欲睡吗? ",
   "4．你在着手做事情时是否感到费力?",
   "5．你在着手做事情时并不感到费力，但当你继续进行时是否感到力不从心?",
   "6．你感觉到体力不够吗?",
   "7．你感觉到你的肌肉力量比以前减小了吗?",
   "8．你感觉到虚弱吗?",
   "9．你集中注意力有困难吗?",
   "10．你在思考问题时头脑象往常一样清晰、敏捷吗?",
   "11．你在讲话时出现口头不利落吗?",
   "12．讲话时，你发现找到一个合适的字眼很困难吗?",
   "13．你现在的记忆力象往常一样吗?",
   "14．你还喜欢做过去习惯做的事情吗?",
 };
 
 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:该量表是主要了解您的疲劳症状的严重程度，请根据您的实际回答“是”或“否”。" };
	
	public QuestionnaireModelOfFS_14() {
		super(QuestionnaireCodeEnum.FS_14);

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
