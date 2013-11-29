package cn.skyduck.questionnaire.questionnaire.MAST;

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
public class QuestionnaireModelOfMAST extends FullQuestionnaireModel implements Serializable{
	
	// 问题
	private static final String[] kQuestionArray = new String[] {
		   "1.你的酒量与多数人一样或更少吗?",
		   "2.你曾有隔天晚上喝酒，次晨醒来想不起隔晚经历的一部分事情吗?",
		   "3.你的配偶、父母或其他近亲曾对你饮酒感到担心或抱怨吗?",
		   "4.当喝了1—2杯酒后，你能不费力地控制不再喝了。",
		   "5.你曾对饮酒感到内疚吗?",
		   "6.你的亲友认为你饮酒和一般人的习惯差不多吗?",
		   "7.当你打算不喝酒了的时候，你可以做到吗?",
		   "8.你参加过戒酒的活动吗?",
		   "9.你曾在饮酒后与人斗殴吗?",
		   "10.你曾因饮酒的问题而与配偶、父母或其它近亲产生矛盾吗? ",
		   "11.你的配偶(或其它家族成员)曾为你饮酒的事而求助他人吗?",
		   "12.你曾因饮酒而导致与好友分手吗?",
		   "13.你曾因饮酒而在工作、学习上出问题吗?",
		   "14.你曾因饮酒被解雇吗?",
		   "15.你曾连续两天以上一直饮酒，而贻误责任，未去工作或置家庭不顾吗?",
		   "16.你经常在上午饮酒吗?",
		   "17.医生曾说你的肝脏有问题或有肝硬化吗?",
		   "18.大量饮酒后，你曾出现震颤谵妄或听到实际上不存在的声音或看到实际上不存在的东西吗?",
		   "19.你曾因为饮酒引起的问题去求助他人吗?（本次求医不算）",
		   "20.你曾因为饮酒引起的问题而住院吗？（本次若住院，则不算）",
		   "21.你曾因为饮酒引起的问题而在精神病院或综合医院精神科住院吗?（本次若住院，则不算）",
		   "22.你曾因饮酒导致的情绪问题而求助于精神科、其他科医生、社会工作者和心理咨询人员吗?（本次求助不算）",
		   "23.你曾因酒后或醉后驾车而被拘留吗?",
		   "24.你曾因为其他的饮酒行为而被拘留几小时以上吗?" 
		   };

	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ "是", "否" },
			// 2
			{ "有过", "没有" },
			// 3
			{ "有过", "没有" },
			// 4
			{ "能", "不能" },
			// 5
			{ "是的", "没有" },
			// 6
			{ "是的", "不是的" },
			// 7
			{ "可以", "不能" },
			// 8
			{ "参加过", "没参加过" },
			// 9
			{ "是的", "没有" },
			// 10
			{ "是的", "没有" },
			// 11
			{ "是的", "没有" },
			// 12
			{ "是的", "没有" },
			// 13
			{ "是的", "没有" },
			// 14
			{ "是的", "没有" },
			// 15
			{ "是的", "没有" },
			// 16
			{ "是的", "没有" },
			// 17
			{ "是的", "没有" },
			// 18
			{ "是的", "没有" },
			// 19
			{ "是的", "没有" },
			// 20
			{ "是的", "没有" },
			// 21
			{ "是的", "没有" },
			// 22
			{ "是的", "没有" },
			// 23
			{ "是的", "没有" },
			// 24
			{ "是的", "没有" }
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本量表自评、他评均可。请根据实际情况回答。" };

	public QuestionnaireModelOfMAST() {
		super(QuestionnaireCodeEnum.MAST);

		// 在这里进行初始化

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
			List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.true_or_false);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfBoolean(answerDataSource, -1);
	}
}
