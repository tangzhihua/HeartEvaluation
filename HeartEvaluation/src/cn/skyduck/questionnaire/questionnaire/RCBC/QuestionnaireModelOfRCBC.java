package cn.skyduck.questionnaire.questionnaire.RCBC;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.FillTesterPersonalInformationFragmentOfChildrenFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.children.RespondentsInformationOfChildren;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.question_frame_fragment.rcbc.RCBC29FragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.rcbc.RCBC30And31FragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
/**
 * 儿童量表
 * @author skyduck
 *
 */
public class QuestionnaireModelOfRCBC extends FullQuestionnaireModel implements Serializable{

	private static final String[] kQuestionArray = new String[]{
		"1、头痛",
    "2、肚子痛或呕吐",
    "3、支气管哮喘或哮喘发作",
    "4、尿床或尿裤子",
    "5、大便在床上或在裤子里",
    "6、发脾气（伴随叫喊或发怒动作）",
    "7、到学校就哭或拒绝上学",
    "8、逃学",
    "9、非常不安，难于长期静坐",
    "10、动作多，乱动，坐立不安",
    "11、经常破坏自己或别人的东西",
    "12、经常与别的儿童打架，或争吵",
    "13、别的孩子不喜欢他",
    "14、经常烦恼，对许多事都心烦",
    "15、经常一个人呆着",
    "16、易激惹或勃然大怒",
    "17、经常表现出痛苦，不愉快流泪或优伤",
    "18、面部或肢体抽动和作态",
    "19、经常吸吮拇指甲或手指",
    "20、经常咬指甲或手指",
    "21、经常不听管教",
    "22、做事拿不定主意",
    "23、害怕新事物和新环境",
    "24、神经质或过分特殊",
    "25、时常说谎",
    "26、欺负别的孩子",
    "27、有没有口吃（说话结巴）",
    "28、有没有言语困难，而不是口吃（如，表达自己转述别人的话有困难）",
    "29、是否偷过东西（请在复选框中选择，若从来没有就不用选）",
    "30、有没有进食的不正常",
    "31、有没有睡眠困难"
	};
 
	// 答案
	private static final String[][] kAnswerArray = new String[][] {
			// 1 ~ 8
			{ "从来没有", "有时出现，不是每周1次", "至少每周1次" },
			// 9 ~ 26
			{ "从来没有", "轻微或有时有 ", "严重或经常出现" },
			// 27 ~ 31
			{ "从来没有", "轻微或有时有", "程度严重或经常出现" }, };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：以下是关于儿童可能发生的行为或心身健康问题，请您的孩子最近1年来的实际情况选择。" };

	public QuestionnaireModelOfRCBC() {
		super(QuestionnaireCodeEnum.RCBC);

		// 在这里进行初始化

		// 问题总数
		super.questionTotal = kQuestionArray.length;

		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(儿童)
		final RespondentsInformationOfChildren answerDataSource = new RespondentsInformationOfChildren(getQuestionnaireCodeEnum());
		framePageFragmentFactory = new FillTesterPersonalInformationFragmentOfChildrenFactoryMethod();
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

		// 1 ~ 27
		for (int i = 0; i < 27; i++) {
			List<String> anwserList = null;
			if (i < 8) {
				anwserList = Arrays.asList(kAnswerArray[0]);
			} else if (i < 26) {
				anwserList = Arrays.asList(kAnswerArray[1]);
			} else {
				anwserList = Arrays.asList(kAnswerArray[2]);
			}
			final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			final QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}
		
		// 28
		framePageFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();
		final InputAndSingleOnePageDataSource questionDataSource28 = new InputAndSingleOnePageDataSource(kQuestionArray[questionIndex], Arrays.asList(kAnswerArray[1]), "如果有请描述其困难程度");
		final QuestionFramePageModel questionFrameModel28 = new QuestionFramePageModel(framePageFragmentFactory);
		questionFrameModel28.setQuestionIndex(questionIndex++);
		questionFrameModel28.setQuestionDataSource(questionDataSource28);
		super.frameModelList.add(questionFrameModel28);

		// 29
		framePageFragmentFactory = new RCBC29FragmentFactoryMethod();
		final QuestionFramePageModel questionFrameModel29 = new QuestionFramePageModel(framePageFragmentFactory);
		questionFrameModel29.setQuestionIndex(questionIndex++);
		super.frameModelList.add(questionFrameModel29);

		// 30
		framePageFragmentFactory = new RCBC30And31FragmentFactoryMethod();
		final InputAndSingleOnePageDataSource questionDataSource30 = new InputAndSingleOnePageDataSource(kQuestionArray[questionIndex], Arrays.asList(kAnswerArray[1]), "如果有请描述其困难程度");
		final QuestionFramePageModel questionFrameModel30 = new QuestionFramePageModel(framePageFragmentFactory);
		questionFrameModel30.setQuestionIndex(questionIndex++);
		questionFrameModel30.setQuestionDataSource(questionDataSource30);
		super.frameModelList.add(questionFrameModel30);

		// 31
		final InputAndSingleOnePageDataSource questionDataSource = new InputAndSingleOnePageDataSource(kQuestionArray[questionIndex], Arrays.asList(kAnswerArray[1]), "如果有请描述其困难程度");
		final QuestionFramePageModel questionFrameModel31 = new QuestionFramePageModel(framePageFragmentFactory);
		questionFrameModel31.setQuestionIndex(questionIndex++);
		questionFrameModel31.setQuestionDataSource(questionDataSource);
		super.frameModelList.add(questionFrameModel31);
	}

	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfRCBC(option, this);
	}
}
