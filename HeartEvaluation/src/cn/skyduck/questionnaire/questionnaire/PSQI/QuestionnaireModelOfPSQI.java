package cn.skyduck.questionnaire.questionnaire.PSQI;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import cn.skyduck.global_data_cache.QuestionnaireCodeEnum;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.FillTesterPersonalInformationOfAdultsFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.fill_tester_personal_information.adults.RespondentsInformationOfAdults;
import cn.skyduck.question_frame_fragment.guidance_language.GuidanceLanguageFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.input_and_single_1.InputAndSingleOnePageDataSource;
import cn.skyduck.question_frame_fragment.psqi.PSQIPartOneFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.psqi.PSQIPartOnePageDataSource;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerFragmentFactoryMethod;
import cn.skyduck.question_frame_fragment.single_answer.SingleAnswerPageDataSource;
import cn.skyduck.questionnaire.IQuestionnaireFramePageFragmentFactoryMethod;
import cn.skyduck.questionnaire.PackAnswerDataTools;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.questionnaire.model.frame_model.BaseFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.NonQuestionFramePageModel;
import cn.skyduck.questionnaire.model.frame_model.subclass.QuestionFramePageModel;

@SuppressWarnings("serial")
public class QuestionnaireModelOfPSQI extends FullQuestionnaireModel implements Serializable {

	// 问题
	private static final String[][] kQuestionArray = new String[][] {
			// 1
			{ "1、过去一个月你通常上床睡觉的时间是几点？", "" },
			// 2
			{ "2.过去一个月你每晚通常要", "分钟才能入睡?" },
			// 3
			{ "3.过去一个月每天早上通常几点起床？", "" },
			// 4
			{ "4.过去一个月你每晚实际睡眠平均", "个小时。" },
			// 5
			{ "5.过去一个月你是否因为 入睡困难(不能在30分钟内入睡) 而经常睡眠不好？" },
			// 6
			{ "6、过去一个月你是否因为 夜间易醒 或 早醒 而经常睡眠不好？" },
			// 7
			{ "7、过去一个月你是否因为 夜间去厕所 而经常睡眠不好？" },
			// 8
			{ "8、过去一个月你是否因为 呼吸不畅 而经常睡眠不好？" },
			// 9
			{ "9、过去一个月你是否因为 在睡眠中咳嗽或打鼾 而经常睡眠不好？" },
			// 10
			{ "10、过去一个月你是否因为 感到寒冷 而经常睡眠不好？" },
			// 11
			{ "11、过去一个月你是否因为 感到太热 而经常睡眠不好？" },
			// 12
			{ "12、过去一个月你是否因为 做不好的梦 而经常睡眠不好？" },
			// 13
			{ "13、过去一个月你是否因为 疼痛不适 而经常睡眠不好？" },
			// 14
			{ "14、过去一个月你是否因为  其它影响睡眠的事情  而经常睡眠不好？", "您可以将其它影响睡眠的事情写在横线上" },
			// 15
			{ "15、你对过去一个月总睡眠质量质量评分" },
			// 16
			{ "16、过去一个月，你是否经常要服药(包括从医生处方或者在外面药店购买)才能入睡？" },
			// 17
			{ "17、过去一个月，你经常感到困倦吗？" },
			// 18
			{ "18、过去一个月，感到做事情的精力不足。" },
			// 19
			{ "19、你是与人同睡一张床吗？(如果此题选A，本次测验结束)" },
			// 20
			{ "20、如果你是与人同睡一床或有室友，请询问他(她)是否发现，你在过去一个月睡觉时出现:打鼾" },
			// 21
			{ "21、如果你是与人同睡一床或有室友，请询问他(她)是否发现，你在过去一个月睡觉时出现:呼吸之间长时间的停顿" },
			// 22
			{ "22、如果你是与人同睡一床或有室友，请询问他(她)是否发现，你在过去一个月睡觉时出现:你的腿抽动或者痉挛" },
			// 23
			{ "23、如果你是与人同睡一床或有室友，请询问他(她)是否发现，你在过去一个月睡觉时出现:不能辩认方向或混乱状态" },
			// 24
			{ "24、如果你是与人同睡一床或有室友，请询问他(她)是否发现，你在过去一个月睡觉时出现:其它睡不安宁的情况", "如果有请描述" } };

	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			// 5-14
			{ "A、过去一个月没有", "B、每周平均不足一个晚上", "C、每周平均一或两个晚上", "D、每周平均三个或更多晚上" },
			// 15
			{ "A、很好", "B、较好", "C、较差", "D、很差" },
			// 16
			{ "A、过去一个月没有", "B、每周平均不足一次", "C、每周平均一或两次", "D、每周平均三次或三次以上" },
			// 17
			{ "A、过去一个月没有", "B、每周平均不足一次", "C、每周平均一或两次", "D、每周平均三次或三次以上" },
			// 18
			{ "A、没有", "B、偶尔有", "C、有时有", "D、经常有" },
			// 19
			{ "A、我就自己睡，屋子里没有伴侣或室友", "B、在同一个屋子但不同房间", "C、在同一个房间但不在同一张床上", "D、是的" },
			// 20-24
			{ "A、过去一个月没有", "B、每周平均不足一个晚上", "C、每周平均一或两个晚上", "D、每周平均三个或更多晚上" } };

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t指导语：下面一些问题是关于您最近一个月的睡眠状况，请选择或填写最符合您实际情况的答案。" };

	public QuestionnaireModelOfPSQI() {
		super(QuestionnaireCodeEnum.PSQI);

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
		IQuestionnaireFramePageFragmentFactoryMethod questionTypeOneFragmentFactory = new PSQIPartOneFragmentFactoryMethod();
		IQuestionnaireFramePageFragmentFactoryMethod questionTypeTwoFragmentFactory = new SingleAnswerFragmentFactoryMethod();
		IQuestionnaireFramePageFragmentFactoryMethod questionTypeThreeFragmentFactory = new InputAndSingleOneFragmentFactoryMethod();

		for (int i = 0; i < kQuestionArray.length; i++) {

			QuestionFramePageModel questionFrameModel = null;

			if (i <= 3) {
				// 这里添加是使用界面类型1的问题 1-4
				final List<String> questionList = Arrays.asList(kQuestionArray[i]);
				final Object questionDataSource = new PSQIPartOnePageDataSource(questionList, "");
				questionFrameModel = new QuestionFramePageModel(questionTypeOneFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				if (i == 0 || i == 2) {
					questionFrameModel.setFragmentStyle(PSQIPartOneFragmentFactoryMethod.FragmentStyleEnum.clicked_open_time_selector);
				} else {
					questionFrameModel.setFragmentStyle(PSQIPartOneFragmentFactoryMethod.FragmentStyleEnum.clicked_open_soft_keyboard);
				}
			}

			// 这里添加是使用界面类型2的问题 5-13 15-23
			if (i >= 4 && i <= 12 || i >= 14 && i < kQuestionArray.length - 1) {
				List<String> anwserList = Arrays.asList(kAnswerArray[0]);
				final List<String> questionList = Arrays.asList(kQuestionArray[i]);
				final SingleAnswerPageDataSource questionDataSource = new SingleAnswerPageDataSource(questionList.get(0), anwserList);
				questionFrameModel = new QuestionFramePageModel(questionTypeTwoFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionDataSource);
				questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			}

			// 这里添加是使用界面类型3的问题 14 24
			if (i == 13 || i == 23) {
				List<String> anwserList = Arrays.asList(kAnswerArray[0]);
				final List<String> questionList = Arrays.asList(kQuestionArray[i]);
				final InputAndSingleOnePageDataSource questionPartTwoDataSource = new InputAndSingleOnePageDataSource(questionList.get(0), anwserList, questionList.get(1));
				questionFrameModel = new QuestionFramePageModel(questionTypeThreeFragmentFactory);
				questionFrameModel.setQuestionIndex(questionIndex++);
				questionFrameModel.setQuestionDataSource(questionPartTwoDataSource);
			}
			super.frameModelList.add(questionFrameModel);

		}
	}

	@Override
	public boolean isTestEffectively() {
		do {
			if (super.isTestEffectively()) {
				break;
			}

			BaseFramePageModel framePageModel = getFrameModel(getCurrentFrameIndex());
			// 1~18题必答, 19~24选答
			if (framePageModel.getQuestionIndex() >= 18) {
				break;
			}
			return false;
		} while (false);

		return true;
	}

	@Override
	public boolean isCanBeIgnoredCurrentQuestion() {
		do {
			if (super.isCanBeIgnoredCurrentQuestion()) {
				break;
			}
			BaseFramePageModel framePageModel = getFrameModel(getCurrentFrameIndex());
			// 1~18题必答, 19~24选答
			if (framePageModel.getQuestionIndex() >= 18) {
				break;
			}
			// 不可忽略
			return false;
		} while (false);

		// 可以忽略
		return true;
	}
	
	public byte[] getPartOneAnswerResults() {
		return PackAnswerDataTools.getPartOneAnswerResultsOfPSQI(this);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfPSQI(option, this);
	}
}
