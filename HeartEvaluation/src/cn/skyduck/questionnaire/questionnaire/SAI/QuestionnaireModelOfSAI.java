package cn.skyduck.questionnaire.questionnaire.SAI;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer_expansion_1.SingleAnswerExpansionOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer_expansion_1.SingleAnswerExpansionOnePageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSAI extends FullQuestionnaireModel implements Serializable{

	// 问题
  private static final String[] kQuestionArray = new String[]{
    //
  	"1、	自杀是一种疯狂的行为。",
  	//
  	"2、自杀死亡者应与自然死亡者享受同样的待遇。",
  	//
  	"3、一般情况下，我不愿意和有过自杀行为的人深交。",
  	//
  	"4、在整个自杀事件中，最痛苦的是自杀者的家属。",
  	//
  	"5、对于身患绝症又极度痛苦的病人，可由医务人员在法律的支持下帮助病人结束生命(主动安乐死)。",
  	//
  	"6、在处理自杀事件过程中，应该对其家属表示同情和关心并尽可能为他们提供帮助。",
  	//
  	"7、自杀是对人生命尊严的践踏。",
  	//
  	"8、不应为自杀死亡者开追悼会。",
  	//
  	"9、如果我的朋友自杀未遂，我会比以前更关心他。",
  	//
  	"10、如果我的邻居家里有人自杀，我会逐渐疏远和他们的关系。",
  	//
  	"11、安乐死是对人生命尊严的践踏。",
  	//
  	"12、自杀是对家庭和社会一种不负责任的行为。",
  	//
  	"13、人们不应该对自杀死亡者评头论足。",
  	//
  	"14、我对那些反复自杀者很反感，因为他们常常将自杀作为一种控制别人的手段。",
  	//
  	"15、对于自杀，自杀者的家属在不同程度上都应负有一定的责任。",
  	//
  	"16、假如我自己身患绝症又处于极度痛苦之中，我希望医务人员能帮助我结束自己的生命。",
  	//
  	"17、个体为某种伟大的、超过人生命价值的目的而自杀是值得赞许的",
  	//
  	"18、一般情况下，我不愿去看望自杀未遂者，即使是亲人或好朋友也不例外。",
  	//
  	"19、自杀只是一种生命现象，无所谓道德上的好与坏。",
  	//
  	"20、自杀未遂者不值得同情。",
  	//
  	"21、对于身患绝症又极度痛苦的病人，可不再为其进行维持生命的治疗(被动安乐死)。",
  	//
  	"22、自杀是对亲人、朋友的背叛。",
  	//
  	"23、人有时为了尊严和荣誉而不得不自杀。",
  	//
  	"24、在交友时，我不太介意对方是否有过自杀行为。",
  	//
  	"25、对自杀未遂者应给予更多的关心与帮助。",
  	//
  	"26、当生命已无欢乐可言时，自杀是可以理解的。",
  	//
  	"27、假如我自己身患绝症又处于极度痛苦之中，我不愿再接受维持生命的治疗。",
  	//
  	"28、一般情况下，我不会和家中有过自杀者的人结婚。",
  	//
  	"29、人应有选择自杀的权利。"
  };
  
  // 备选答案
  private static final String[] kAnswerArray = new String[]{
    "1分",
    "2分",
    "3分",
    "4分",
    "5分"
  };
  
  //指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：本问卷旨在了解国人对自杀的态度，以期为我国的自杀预防工作提供资料与指导，在下列每个问题的后面都标有1，2，3，4，5，五个数字供您选择，数字1~5分别代表您对问题从完全赞同到完全不赞同的态度，请根据您的选择圈出相应的数字。谢谢合作！" };
	
	public QuestionnaireModelOfSAI() {
		super(QuestionnaireCodeEnum.SAI);
		
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
		framePageFragmentFactory = new SingleAnswerExpansionOneFragmentFactoryMethod();
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final SingleAnswerExpansionOnePageDataSource questionDataSource = new SingleAnswerExpansionOnePageDataSource(kQuestionArray[i], anwserList, "(1分:完全赞同)", "(5分:完全不赞同)");
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
