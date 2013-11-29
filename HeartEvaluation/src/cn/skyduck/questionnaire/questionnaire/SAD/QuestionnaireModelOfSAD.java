package cn.skyduck.questionnaire.questionnaire.SAD;

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
public class QuestionnaireModelOfSAD extends FullQuestionnaireModel implements Serializable{

 //问题
 private static final String[] kQuestionArray = new String[]{
	 "1.即使在不熟悉的社交场合里我仍感到放松。",
   "2.我尽量避免迫使我参加交际应酬的情形",
   "3.我同陌生人在一起时很容易放松 ",
   "4.我并不特别想去回避人们",
   "5.我通常发现社交场合令人心烦意乱",
   "6.在社交场合我通常感觉平静及舒适",
   "7.在同异性交谈时，我通常感觉放松",
   "8.我尽量避免与人家讲话，除非特别熟",
   "9.如果有同新人相会的机会，我会抓住的",
   "10.在非正式的聚会上如有异性参加，我通常觉得焦虑和不安",
   "11.我通常与人们在一起时感到焦虑，除非与他们特别熟",
   "12.我与一群人在一起时通常感到放松",
   "13.我经常想离开人群",
   "14.在置身于不认识的人群中时，我感到不自在",
   "15.在初次遇见某些人时，我通常是放松的",
   "16.被介绍给别人使我感到紧张和焦虑",
   "17.尽管满房间都是生人，我可能还是会进去的",
   "18.我会避免走上前去加入到一大群人中间",
   "19.当上司想同我谈话时，我很高兴与他谈话",
   "20.当与一群人在一起时，我通常感觉忐忑不安",
   "21.我喜欢躲开人群",
   "22.在晚上或社交聚会上与人们交谈对我不成问题",
   "23.在一大群人中间，我极少能感到自在",
   "24.我经常想出一些借口以回避社交活动",
   "25.我有时充当为人们相互介绍的角色",
   "26.我尽量避开正式的社交场合",
   "27.我通常参加我所能参加的各种社会交往。不管是什么社交活动，我一般是能去就去",
   "28.我发现同他人在一起时放松很容易",
 };
 
	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:以下有一些问题需要你回答，请根据自己的实际情况和反应，选择最正确的答案。" };
	
	public QuestionnaireModelOfSAD() {
		super(QuestionnaireCodeEnum.SAD);
		

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
