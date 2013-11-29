package cn.skyduck.questionnaire.questionnaire.SERS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfSERS extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
     "1、身体疲倦",
     "2、头痛",
     "3、睡眠障碍",
     "4、头晕",
     "5、直立性虚脱",
     "6、心悸",
     "7、震颤",
     "8、出汗",
     "9、口干",
     "10、便秘",
     "11、排尿障碍",
     "12、嗜睡",
     "13、性功能障碍",
     "14、其它症状："
 };
 
 // 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1
			{ "(0)无",
				"(1)轻度疲劳，但不需要额外的休息", 
				"(2)有时或非常疲劳而不得不卧床和休息", 
				"(3)整天卧床"},
			// 2
			{ "(0)无",
				"(1)偶尔", 
				"(2)持续性中度头痛或偶尔严重头痛", 
				"(3)持续的严重头痛"},
			// 3
			{ "(0)正常睡眠",
				"(1)轻度睡眠障碍", 
				"(2)只睡3小时", 
				"(3)睡眠少于3小时"},
			// 4
			{ "(0)无",
				"(1)偶尔轻度头晕", 
				"(2)持续性地轻度头晕", 
				"(3)持续性地头晕而不得不躺下"},
			// 5
			{ "(0)无",
				"(1)轻度", 
				"(2)中度", 
				"(3)重度"},
			// 6
			{ "(0)无",
				"(1)稍有些心悸", 
				"(2)有时心悸", 
				"(3)经常心悸"},
			// 7
			{ "(0)无",
				"(1)轻度震颤，活动不受到损伤", 
				"(2)中度震颤", 
				"(3)严重的震颤"},
			// 8
			{ "(0)无",
				"(1)轻度增加(手心湿)", 
				"(2)明显增加(衣服湿)", 
				"(3)出汗甚多(多次换衣服)"},
			// 9
			{ "(0)无",
				"(1)有些，但没有主观的不适感", 
				"(2)明显，但不严重或不觉痛苦", 
				"(3)严重，说话困难"},
			// 10
			{ "(0)无",
				"(1)有些便秘", 
				"(2)确实有便秘问题", 
				"(3)4天或4天以上没有排便运动"},
			// 11
			{ "(0)无",
				"(1)排尿有些困难", 
				"(2)在排空膀胱时确有困难，需要治疗", 
				"(3)尿潴留"},
			// 12
			{ "(0)无",
				"(1)轻度", 
				"(2)中度，对日常生活有些妨碍", 
				"(3)严重，影响每日的常规工作"},
			// 13
			{ "(0)无",
				"(1)轻度", 
				"(2)中度", 
				"(3)严重"},
			// 14
			{ "(0)无",
				"(1)轻度", 
				"(2)中度", 
				"(3)重度"} };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语:此量表为他评量表。建议根据受测者的口述以及对受测者的观察评定。" };

	public QuestionnaireModelOfSERS() {
		super(QuestionnaireCodeEnum.SERS);

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
			QuestionFramePageModel questionFrameModel = null;
			if (i == kQuestionArray.length - 1) {
				IQuestionnaireFramePageFragmentFactoryMethod questionTypeThreeFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
				final InputAndSingleOnePageDataSource questionPartTwoDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[i], anwserList, "其他症状:");
				questionFrameModel = new QuestionFramePageModel(questionTypeThreeFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionPartTwoDataSource);
			} else {
				final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
				questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			}
			super.frameModelList.add(questionFrameModel);
		}
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfSERS(option, this);
	}
}
