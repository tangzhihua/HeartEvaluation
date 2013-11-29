package cn.skyduck.questionnaire.questionnaire.SAS;

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
public class QuestionnaireModelOfSAS extends FullQuestionnaireModel implements Serializable {

	// 问题
	private static final String[] kQuestionArray = new String[] { 
		"1、我觉得比平时容易紧张或着急", 
		"2、我无缘无故在感到害怕", 
		"3、我容易心里烦乱或感到惊恐", 
		"4、我觉得我可能将要发疯", 
		"5、我觉得一切都很好", 
		"6、我手脚发抖打颤", 
		"7、我因为头疼、颈痛或背痛而苦恼", 
		"8、我觉得容易衰弱或疲乏",
		"9、我觉得心平气和，并且容易安静坐着", 
		"10、我觉得心跳的很快 ", 
		"11、我因为一阵阵头晕而苦恼", 
		"12、我有晕倒发作，或觉得要晕倒似的", 
		"13、我吸气呼气都感到很容易", 
		"14、我的手脚麻木和刺痛", 
		"15、我因为胃痛和消化不良而苦恼", 
		"16、我常常要小便", 
		"17、我的手脚常常是干燥温暖的", 
		"18、我脸红发热",
		"19、我容易入睡并且一夜睡得很好", 
		"20、我做恶梦" };

	// 备选答案
	private static final String[] kAnswerArray = new String[] { "A. 没有或很少时间", "B. 小部分时间", "C. 相当多时间", "D. 绝大部分或全部时间" };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面有二十条文字，请仔细阅读每一条，把意思弄明白，然后根据您最近一星期的实际情况来选择。" };

	public QuestionnaireModelOfSAS() {
		super(QuestionnaireCodeEnum.SAS);

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
	
	/**
	 * 跟据问题索引 和 答案数据源 得到该问题答案值
	 * 
	 * @param answerDataSource
	 * @param questionIndex
	 * @return
	 */
	public byte getAnswerValueResult(final Object answerDataSource, final int questionIndex) {
		if (!(answerDataSource instanceof Integer)) {
			// 用户答案非法, 或者用户没有选择答案, 该题就要略过
			return 0;
		}

		final Integer answerOption = (Integer) answerDataSource + 1;

		return answerOption.byteValue();
	}
	
	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
