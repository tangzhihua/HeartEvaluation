package cn.skyduck.questionnaire.questionnaire.NOSIE;

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
public class QuestionnaireModelOfNOSIE extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、肮脏",
   "2、不耐烦",
   "3、哭泣",
   "4、对周围活动感兴趣",
   "5、不督促就一直坐着",
   "6、容易生气",
   "7、听到不存在的声音",
   "8、衣着保持整洁",
   "9、对人友好",
   "10、不如意便心烦",
   "11、拒绝做日常事务",
   "12、易激动发牢骚",
   "13、忘记事情",
   "14、问而不答",
   "15、对好笑的事发笑",
   "16、进食狼籍",
   "17、与人攀谈",
   "18、自觉抑郁沮丧",
   "19、谈论个人爱好",
   "20、看到不存在的东西",
   "21、提醒后才做事",
   "22、不督促便一直睡着",
   "23、自觉一无是处",
   "24、不太遵守医院规则",
   "25、难以完成简单任务",
   "26、自言自语",
   "27、行动缓慢",
   "28、无故发笑",
   "29、容易冒火",
   "30、保持自身整洁"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"无",
 		"有时有",
 		"常常",
 		"经常",
 		"一直是"
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：该量表由临床护士依据对住院的成年精神病人病情的纵向观察，评定病人的行为障碍、病情的演变及治疗效果，做出相应的选择。" };
	
	public QuestionnaireModelOfNOSIE() {
		super(QuestionnaireCodeEnum.NOSIE);
		

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
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}

	}


}
