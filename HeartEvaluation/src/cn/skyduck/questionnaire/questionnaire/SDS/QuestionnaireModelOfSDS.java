package cn.skyduck.questionnaire.questionnaire.SDS;

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
public class QuestionnaireModelOfSDS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1、我觉得闷闷不乐，情绪低沉",
   "2、我觉得一天之中早晨最好 ",
   "3、我一阵阵哭出来或觉得想哭",
   "4、我晚上睡眠不好",
   "5、我吃的跟平常一样多",
   "6、我与异性亲密接触时和以往一样感觉愉快",
   "7、我发觉我的体重在下降",
   "8、我有便秘的苦恼",
   "9、我心跳比平时快",
   "10、我无缘无故地感到疲乏",
   "11、我的头脑跟平常一样清楚 ",
   "12、我觉得经常做的事情并没有困难",
   "13、我觉得不安而平静不下来",
   "14、我对将来抱有希望",
   "15、我比平常容易生气激动",
   "16、我觉得作出决定是容易的",
   "17、我觉得自己是个有用的人，有人需要我",
   "18、我的生活过得很有意思",
   "19、我认为如果我死了别人会生活得好些",
   "20、平常感兴趣的事我仍然照样感兴趣"
 };
 
 	//备选答案
 	private static final String[] kAnswerArray = new String[]{
 		"A. 没有或很少时间",
 		"B. 小部分时间",
 		"C. 相当多时间",
 		"D. 绝大部分或全部时间" 
 	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面有二十个题目，请仔细阅读每一条，把意思弄明白，然后根据您最近一星期的实际情况来选择。" };

	public QuestionnaireModelOfSDS() {
		super(QuestionnaireCodeEnum.SDS);

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
