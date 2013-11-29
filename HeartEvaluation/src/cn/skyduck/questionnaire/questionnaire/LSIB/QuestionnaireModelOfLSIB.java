package cn.skyduck.questionnaire.questionnaire.LSIB;

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
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfLSIB extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
     "一、你这个年纪最大的好处是什么?",
     "二、今后五年你打算做什么?你估计今后的生活会有什么变化?",
     "三、你现在生活中最重要的事情是什么?",
     "四、与早期的生活相比，你现在是否幸福?",
     "五、你是否曾担心人们期望你做的事你却不能胜任--你无法满足人们对你的要求?",
     "六、如果你想怎样就能怎样，那么你最喜欢生活在哪里(国家名)?",
     "七、你感到孤独的时间有多少?",
     "八、你感到生活无目的的时间有多少?",
     "九、你希望将来与好朋友在一起的时间更多一些还是自己独处的时间更多一些?",
     "十、你在目前的生活中发现多少不幸的事情?",
     "十一、当你年迈之后，事情比原先想象得好还是不好?",
     "十二、你对自己生活的满意程度如何?"
 };
 
 // 备选答案
 private static final String[][] kAnswerArray = new String[][]{
	 {"1、积极的答案", "2、没有任何好处"},
	 {"1、变好，或无变化", "2、无法预料，\"各种可能性都有\"", "3、变坏"},
	 {"1、任何自身之外的事情，或令人愉快的对未来的解释", "2、\"维持现状\"、保持健康或工作", "3、摆脱当前困境；或\"没有重要的事情\"；或提起往事。"},
	 {"1、现在最幸福；或同样幸福；或比较不出何时更幸福。", "2、最近几年有些不如以前了", "3、以前比现在好，目前是最糟糕的时期"},
	 {"1、不曾担心", "2、略有些担心", "3、担心"},
	 {"1、目前所在地", "2、任何其它地方"},
	 {"1、从未有过", "2、有时", "3、经常，十分频繁"},
	 {"1、从未有过", "2、有时", "3、经常，十分频繁"},
	 {"1、现在这样很好", "2、与好朋友在一起的时间更多一些。", "3、自己独处的时间更多一些"},
	 {"1、几乎没有", "2、有一些", "3、许多"},
	 {"1、好", "2、和预期的差不多", "3、不好"},
	 {"1、非常满意", "2、相当满意", "3、不太满意"}
 };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:此量表为他评量表。请受测者就以下问题发表意见。" };

	public QuestionnaireModelOfLSIB() {
		super(QuestionnaireCodeEnum.LSIB);
		

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
			final List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

	}

}
