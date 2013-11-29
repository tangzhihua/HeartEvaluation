package cn.skyduck.questionnaire.questionnaire.ASLEC;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.aslec.ASLECPictureFactoryMethod;
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
public class QuestionnaireModelOfASLEC extends FullQuestionnaireModel implements Serializable{

//问题
 private static final String[] kQuestionArray = new String[]{
	 "1.被人误会或错怪",
	 "2．受人歧视冷遇",
	 "3．考试失败或不理想",
	 "4．与同学或好友发生纠纷",
	 "5．生活习惯(饮食、休息等)明显变化",
	 "6．不喜欢上学",
	 "7．恋爱不顺利或失恋",
	 "8．长期远离家人不能团聚",
	 "9．学习负担重",
	 "10．与老师关系紧张",
	 "11．本人患急重病",
	 "12．亲友患急重病",
	 "13．亲友死亡",
	 "14．被盗或丢失东西",
	 "15．当众丢面子",
	 "16．家庭经济困难",
	 "17．家庭内部有矛盾",
	 "18．预期的评选(如三好学生)落空",
	 "19．受批评或处分",
	 "20．转学或休学",
	 "21．被罚款",
	 "22．升学压力",
	 "23．与人打架",
	 "24．遭父母打骂",
	 "25．家庭给你施加学习压力",
	 "26．意外惊吓，事故",
	 "27．其他事件"
 };
//备选答案
	private static final String[] kAnswerArray = new String[]{
		  "没有",
	    "轻度",
	    "中度",
	    "重度",
	    "极重"
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { 
		"\t\t\t\t指导语:过去12个月（根据所选评定时间范围而变化）内，你和你的家庭是否发生过下列事件？请仔细阅读下列每一个项目，如某事发生过，请根据事件给你造成的苦恼程度进行选择。如果某事未发生那么选择未发生即可。" };

	public QuestionnaireModelOfASLEC() {
		super(QuestionnaireCodeEnum.ASLEC);

		// 在这里进行初始化

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		// 评定时间范围(3个月, 6个月, 9个月, 12个月)
		answerDataSource.addExpandOption(RespondentsInformationOfAdults.ExpandOptionEnum.assessmentTimeRange);
		answerDataSource.addNecessaryOption(RespondentsInformationOfAdults.NecessaryOptionEnum.assessmentTimeRange);
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
		framePageFragmentFactory = new ASLECPictureFactoryMethod();
		final List<String> anwserList = Arrays.asList(kAnswerArray);
		for (int i = 0; i < kQuestionArray.length; i++) {
			if (i == kQuestionArray.length - 1) {
				framePageFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
				final InputAndSingleOnePageDataSource questionDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[i], anwserList, "若有，请填写在横线上：");
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				questionFrameModel.setFragmentStyle(InputAndSingleOneFragmentFactoryMethod.FragmentStyleEnum.horizontal);
				super.frameModelList.add(questionFrameModel);
			} else {
				framePageFragmentFactory = new SingleAnswerFragmentFactoryMethod();
				final Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
				final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
				questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.horizontal);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				super.frameModelList.add(questionFrameModel);
			}

		}
	}

	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfASLEC(this);
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfASLEC(option, this);
	}
}
