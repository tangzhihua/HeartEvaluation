package cn.skyduck.questionnaire.questionnaire.CES_D;

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
public class QuestionnaireModelOfCES_D extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、一些通常并不困扰我的事使我心烦。",
   "2、我不想吃东西；我胃口不好。",
   "3、即使有家人和朋友的帮助，我也无法摆脱心中的苦闷。",
   "4、我感觉同别人一样好。",
   "5、我很难集中精力做事。",
   "6、我感到压抑。",
   "7、我感到做什么事都很吃力。",
   "8、我觉得未来有希望。",
   "9、我觉得我的生活是失败的。",
   "10、我感到恐惧。",
   "11、我睡眠情况不好。",
   "12、我很幸福。",
   "13、我比平时话少了。",
   "14、我感到孤独。",
   "15、人们对我不友好。",
   "16、我生活快乐，有意义。",
   "17、我的手脚常常是干燥温暖的",
   "18、我感到忧愁。",
   "19、我觉得人们不喜欢我。",
   "20、我觉得无法继续我的日常工作。"
 };
 	
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"A、偶尔或无（少于1天）",
 		"B、少有（1-2天）",
 		"C、时常有（3-4天）",
 		"D、几乎一直有（5-7天）"
 	};

 	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面有二十个题目，请仔细阅读每一条，把意思弄明白，然后根据您最近一星期的实际情况来选择。" };
	
	public QuestionnaireModelOfCES_D() {
		super(QuestionnaireCodeEnum.CES_D);
		

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
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
