package cn.skyduck.questionnaire.questionnaire.Y_BOCS;

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
public class QuestionnaireModelOfY_BOCS extends FullQuestionnaireModel implements Serializable{

	// 问题
	private static final String[] kQuestionArray = new String[] {
			"一、强迫思维占据时间：你有多少时间被强迫思维所占据？是否经常出现？（不包括非强迫性的、与自我相协调的、过分而合理的反复思考，或沉缅于这种想法。）",
			"二、社交或工作能力受强迫思维影响的程度：强迫思维使你在社交或工作中受到多少干扰？有没有因此而使你不能完成某件事情？（如果患者现在没有工作，那么假设患者在工作，以评定其受干扰强度。）",
			"三、强迫思维所致痛苦烦恼程度：你感受到多少痛苦烦恼？（对于大多数病人而言，这种痛苦也就等于焦虑，但也有例外。如，病人会诉说感“烦恼不安”，但否认有“焦虑”。在此只评定由强迫思维所致焦虑，而非广泛性焦虑或与其它症状有关的焦虑。）",
			"四、对强迫思维的抵制：你做过多少努力来摆脱强迫思维？一旦强迫思维出现，你多少次试图转移注意力或不理会它？（在此对试图摆脱强迫思维所做的努力作评定，而不论事实上成功与否。） ",
			"五、控制强迫思维的程度：你能控制住多少强迫思维？你成功地阻止或转移了多少强迫思维？",
			"六、你在强迫行为上用了多少时间：你有多少时间用于强迫行为上？是否经常出现？（如果强迫行为主要表现为有关日常生活的仪式动作，则作以下提问：）你在日常活动中出现仪式动作时，完成这项活动所用时间比正常人增加多少？（大多数病人的强迫动作是强迫性行为表现，如反复洗手，但也有些病人的强迫行为不容易被人察觉，如默默地反复核对。）",
			"七、受强迫行为干扰的程度：强迫行为使你在社交或在工作中受到多少干扰？有没有因此使你不能做某些事情？（如果目前没有工作，则假定病人在工作来评定其受干扰程度）。 ",
			"八、强迫行为所致痛苦烦恼程度：如果阻止你正在进行中的强迫行为，你会有什么感觉？（过一会儿再问以下问题）你会变得怎样焦虑？（在此指突然终止病人的强迫行为而不予保证会允许再做时，评定病人所体验到的痛苦烦恼程度。对大多数病人而言，执行强迫行为时会减少焦虑，所以在作以上评定时，若检查者确定病人的焦虑确实在阻止执行强迫行为后反而减少了，那么再问）：在进行强迫行为、直至完成并感到满意为止的这个时期内，你感受到多少不安？",
			"九、对强迫行为产生的抵制程度：你做了多少努力以摆脱强迫行为？（只评所作的努力，而不论事实上成功与否。）", "十、控制强迫行为的程度：你想执行强迫行为的内心驱动力有多强？（过一会儿再问以下问题）你能控制住多少强迫行为？" };

	// 备选答案
	private static final String[][] kAnswerArray = new String[][] {
			//
			{ "1、无。", 
				"2、轻度。偶尔出现（一天内少于1小时）", 
				"3、中度。经常出现（一天内1-3小时）", 
				"4、重度。频繁出现（一天内3-8小时）", 
				"5、极重度。近乎持续出现（一天内超过8小时）" },
			//
			{ "1、无 。", 
				"2、轻度。轻度影响社交或工作，但整体活动未受影响。", 
				"3、中度。肯定影响社交或工作，但还可加以控制。", 
				"4、重度。社交或工作受到相应程度的损害。", 
				"5、极重度。丧失社交或工作能力。" },
			//
			{ "1、无。", 
				"2、轻度。较少有痛苦烦恼，且程度较轻。", 
				"3、中度。经常有痛苦烦恼，但还能控制。", 
				"4、重度。感明显痛苦烦恼，且次数很多。", 
				"5、极重度。近乎持续感烦恼，以至什么事情都不能做。" },
			//
			{ "1、一直努力去克服强迫思维，或者症状轻微而无需主动去抵制。", 
				"2、大部分时间里试图去克服。", 
				"3、做过一些努力试图去克服。", 
				"4、服从于所有强迫思维而没有克服的企图，但有些勉强。", 
				"5、完全并且乐意服从于所有的强迫思维。" },
			//
			{ "1、完全能控制。", 
				"2、基本能控制。能通过做些努力和集中思想来阻止或转移强迫思维。", 
				"3、能控制一些。有时能阻止或转移强迫思维。 ", 
				"4、很少能控制。很少能成功地阻止强迫思维的进行。很难因转移注意力而摆脱强迫思维。", 
				"5、完全不能控制。完全无意地在体验强迫思维，很少能甚至仅是瞬间地摆脱强迫思维。 " },
			//
			{ "1、无。", 
				"2、轻度（每天少于1小时），或偶尔出现。", 
				"3、中度（每天1-3小时），或频繁出现（一天多于8次，但多数时间里没有）。", 
				"4、重度（每天3-8小时），或出现非常频繁（一天多于8次，且多数时间里都有）。", 
				"5、极重度（每天多于8小时），或几乎持续性出现（出现次数太多而无法统计，并且几乎每个小时都出现数次）。 " },
			//
			{ "1、无。", 
				"2、轻度。轻度干扰社交或工作。但整体活动未受影响。 ", 
				"3、中度。明显干扰社交或工作，但还能控制。", 
				"4、重度。导致社交或工作相当程度受损。", 
				"5、极重度。丧失社交或工作能力。" },
			//
			{ "1、无。", 
				"2、轻度。阻止强迫行为后仅有轻度焦虑或在进行强迫行为时只有轻度焦虑", 
				"3、中度。在强迫行为受阻时，焦虑有所增加，但仍可忍受，或在执行强迫行为时，焦虑有所增加而仍可忍受。", 
				"4、重度。在执行强迫行为时，或被阻止执行时，出现显著持久的焦，虑且越来越感不安。", 
				"5、极重度。对指在改变强迫行为的任何干预，或在执行强迫行为时焦虑体验难以忍受。" },
			//
			{ "1、总在努力试图摆脱强迫行为，或症状轻微而无需摆脱。", 
				"2、大多数时间在试图摆脱。", 
				"3、做过一些努力欲摆脱。", 
				"4、执行所有的强迫行为，没有想控制它们的企图，但做时有些勉强。", 
				"5、完全并心甘情愿地执行所有的强迫行为。" },
			//
			{ "1、完全控制。 ", 
				"2、基本能控制。感到有压力要去执行强迫行为，但往往能自主地控制住。", 
				"3、部分能控制。感到强烈的压力必须去执行强迫行为，不努力的话便控制不住。", 
				"4、很少能控制。有很强烈的欲望去执行强迫行为，费尽心力也只能延迟片刻。", 
				"5、不能控制。完全不由自主地欲望去执行强迫行为，即使作片刻的延迟也几乎不能。" } 
	};

	// 指导语
	private static final String[] kGuidanceLanguage = new String[] { "\t\t\t\t此量表为他评量表。主要用以评估已被诊断为强迫症的病人，其强迫症状的严重程度和治疗效果。可一周评定一次，也可以根据实际情况由医生自行确定。" };

	public QuestionnaireModelOfY_BOCS() {
		super(QuestionnaireCodeEnum.Y_BOCS);

		// 问题总数
		super.questionTotal = kQuestionArray.length;
		IQuestionnaireFramePageFragmentFactoryMethod framePageFragmentFactory = null;

		// 用户信息录入界面(成人)
		final RespondentsInformationOfAdults answerDataSource = new RespondentsInformationOfAdults(getQuestionnaireCodeEnum());
		answerDataSource.addExpandOption(RespondentsInformationOfAdults.ExpandOptionEnum.numberOfTest);
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
			List<String> anwserList = Arrays.asList(kAnswerArray[i]);
			Object questionDataSource = new SingleAnswerPageDataSource(kQuestionArray[i], anwserList);
			QuestionFramePageModel questionFrameModel = new QuestionFramePageModel(framePageFragmentFactory);
			questionFrameModel.setQuestionIndex(questionIndex++);
			questionFrameModel.setQuestionDataSource(questionDataSource);
			questionFrameModel.setFragmentStyle(SingleAnswerFragmentFactoryMethod.FragmentStyleEnum.vertical);
			super.frameModelList.add(questionFrameModel);
		}

	}

	public byte getAnswerValueResultFromAnswerDataSource(final Object answerDataSource) {
		return PackAnswerDataTools.getAnswerValueResultFromAnswerDataSourceOfIntegerType1(answerDataSource);
	}
	
	public String getPartTwoAnswerResultsString(final int option) {
		return PackAnswerDataTools.getPartTwoAnswerResultsStringDataOfY_BOCS(option, this);
	}
}
