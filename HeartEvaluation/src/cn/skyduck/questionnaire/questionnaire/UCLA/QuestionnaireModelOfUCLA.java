package cn.skyduck.questionnaire.questionnaire.UCLA;

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
public class QuestionnaireModelOfUCLA extends FullQuestionnaireModel implements Serializable{

	// 问题 标题
	private static final String[] kQuestionArray = new String[]{
		      "1、你常感到与周围人的关系和谐吗？",
	        "2、你常感到缺少伙伴吗？ ",
	        "3、你常感到没人可以信赖吗？",
	        "4、你常感到寂寞吗？",
	        "5、你常感到属于朋友们中的一员吗？",
	        "6、你常感到与周围的人有许多共同点吗？",
	        "7、你常感到与任何人都不亲密了吗？",
	        "8、你常感到你的兴趣与想法与周围的人不一样吗？",
	        "9、你常感到想要与人来往、结交朋友吗？",
	        "10、你常感到与人亲近吗？ ",
	        "11、你常感到与人亲近吗？",
	        "12、你常感到你与别人来往毫无意义吗？ ",
	        "13、你常感到没有人很了解你吗？ ",
	        "14、你常感到与别人隔开了吗？",
	        "15、你常感到当你愿意时就能找到伙伴吗？",
	        "16、你常感到有人真正了解你吗？",
	        "17、你常感到羞怯吗？",
	        "18、你常感到有人围着你但并不关心你吗？",
	        "19、你常感到有人愿意与你交谈吗？",
	        "20、你常感到有人值得你信赖吗？"
	};
 
	// 备选答案
	private static final String[] kAnswerArray = new String[]{
			   "从不 ",               
			   "很少",
			   "有时",
			   "一直"
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:下面是人们有时出现的一些感受。对于每项描述，请指出你具有那种感觉的频度。" };
	
	public QuestionnaireModelOfUCLA() {
		super(QuestionnaireCodeEnum.UCLA);
		
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
		final List<String> anwserList = Arrays.asList(kAnswerArray);

		for (int i = 0; i < kQuestionArray.length; i++) {
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
			super.frameModelList.add(questionFrameModel);
		}
	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
}
